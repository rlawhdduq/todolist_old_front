package todolist.old_front.controller.rest;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import todolist.old_front.dto.auth.AuthInfoDto;
import todolist.old_front.dto.follow.FollowDto;
import todolist.old_front.dto.user.AuthUserDto;
import todolist.old_front.dto.user.JoinUserDto;
import todolist.old_front.dto.user.LoginUserDto;
import todolist.old_front.service.auth.impl.AuthServiceImpl;
import todolist.old_front.service.follow.impl.FollowServiceImpl;
import todolist.old_front.service.user.impl.UserServiceImpl;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/user")
public class UserApi {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private FollowServiceImpl followService;
    private static final Logger log = LoggerFactory.getLogger(UserApi.class);
    
    @RequestMapping(path="/{userId}", method=RequestMethod.GET)
    public AuthUserDto getUser(@PathVariable String id)
    {
        AuthUserDto authUserDto = userService.getUser(id);
        return authUserDto;
    }

    @RequestMapping(method=RequestMethod.POST)
    public String joinUser(JoinUserDto joinUserDto, HttpServletRequest request)
    {
        AuthUserDto authUserDto = userService.joinUser(joinUserDto);
        String token = authService.getToken(new AuthInfoDto(authUserDto.getUser_id(), authUserDto.getId(), authUserDto.getUser_type()));
        createUserSession(authUserDto, request, token);
        return "redirect:/";
    }

    @RequestMapping(path="/login", method=RequestMethod.POST)
    public ResponseEntity<Object> loginUser(LoginUserDto loginUserDto, @RequestParam(defaultValue="/") String redirectUrl, HttpServletRequest request)
    {
        try
        {
            // 1. 로그인 처리 후
            AuthUserDto authUserDto = userService.loginUser(loginUserDto);
            if( authUserDto == null)
            {
                throw new UsernameNotFoundException("사용자 정보가 일치하지 않습니다");
            }
            
            // 2. 토큰값을 받아온다
            String token = authService.getToken(new AuthInfoDto(authUserDto.getUser_id(), authUserDto.getId(), authUserDto.getUser_type()));
            if( token == null )
            {
                throw new SecurityException("토큰 발급 실패");
            }
        
            // 수동 로그인 처리
            // 3. Security가 읽을 수 있도록 수동으로 인증 정보 생성
            // 일단 야매권한이라도 넣어둠, 추후 권한생기면 authorities 수정하면 된다.
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            
            // Security가 인식할 수 있도록 표준 User 객체를 만들어준다.
            User userPrincipal = new User(authUserDto.getId(), "[PROTECTED]", authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);

            // 4. 세션 생성 및 SecurityContext 저장
            HttpSession session = createUserSession(authUserDto, request, token);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        }
        catch (UsernameNotFoundException | SecurityException e) 
        {
            // 5. 로그인 실패 시 (Id가 없거나, 토큰 발급 실패)
            log.error("로그인 실패 (UsernameNotFound): {}", e.getMessage());
            HttpHeaders errorHeaders = new HttpHeaders();
            try { errorHeaders.setLocation(new URI("/user/login?error")); } catch (Exception ex) { /* 무시 */ }
            return new ResponseEntity<>(errorHeaders, HttpStatus.FOUND);
        } 
        catch (Exception e) {
            // 5b. (WebClient가 4xx/5xx를 반환한 경우 등)
            log.error("로그인 실패 (기타): {}", e.getMessage());
            HttpHeaders errorHeaders = new HttpHeaders();
            try { errorHeaders.setLocation(new URI("/user/login?error")); } catch (Exception ex) { /* 무시 */ }
            return new ResponseEntity<>(errorHeaders, HttpStatus.FOUND);
        }

        // 6. 로그인 성공 시
        HttpHeaders successHeaders = new HttpHeaders();
        try {
            successHeaders.setLocation(new URI(redirectUrl)); // "/" 또는 지정된 URL로 302 리다이렉트
        } catch (Exception e) {
            try { successHeaders.setLocation(new URI("/")); } catch (Exception ex) { /* 무시 */ }
        }
        
        return new ResponseEntity<>(successHeaders, HttpStatus.FOUND); // 302
    }

    @RequestMapping(path="/logout", method=RequestMethod.POST)
    public ResponseEntity<Object> logOut(HttpServletRequest request)
    {
        log.info("수동 로그아웃 처리 시작");
        try {
            SecurityContextHolder.clearContext();
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
                log.info("세션 무효화 완료");
            }
        } catch (Exception e) { log.error("로그아웃 처리 중 에러", e); }

        HttpHeaders headers = new HttpHeaders();
        try { headers.setLocation(new URI("/user/login?logout")); } catch (Exception e) { /* 무시 */ }
        return new ResponseEntity<>(headers, HttpStatus.FOUND); // 302
    }

    /**
     *  회원가입 or 로그인 후 세션 등록에 사용할 공통 메서드
     */
    private HttpSession createUserSession(AuthUserDto authUserDto, HttpServletRequest request, String token)
    {
        if(authUserDto == null)
            return null;
        HttpSession session = request.getSession(true);
        session.setAttribute("token", token);
        session.setAttribute("loginUser", authUserDto);
        return session;
    }
    
}
