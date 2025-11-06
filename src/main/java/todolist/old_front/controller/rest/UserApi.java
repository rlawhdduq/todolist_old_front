package todolist.old_front.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @RequestMapping(path="/{userId}", method=RequestMethod.GET)
    public AuthUserDto getUser(@PathVariable Long userId)
    {
        AuthUserDto authUserDto = userService.getUser(userId);
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
    public String loginUser(LoginUserDto loginUserDto, @RequestParam(defaultValue="/") String redirectUrl, HttpServletRequest request)
    {
        // 1. 로그인 처리 후
        AuthUserDto authUserDto = userService.loginUser(loginUserDto);
        // 2. 토큰값을 받아온다
        String token = authService.getToken(new AuthInfoDto(authUserDto.getUser_id(), authUserDto.getId(), authUserDto.getUser_type()));

        System.out.println("Url :: "+redirectUrl);
        System.out.println("token :: "+token);
        if( authUserDto != null && token != null)
        {
            createUserSession(authUserDto, request, token);
        }
        else // 로그인 실패 시
        {
            return "redirect:/user/login?error";
        }
        return "redirect:" + redirectUrl;
    }

    /**
     *  회원가입 or 로그인 후 세션 등록에 사용할 공통 메서드
     */
    private void createUserSession(AuthUserDto authUserDto, HttpServletRequest request, String token)
    {
        if(authUserDto == null)
            return;
        Map<String, List<Long>> followList = followService.getFollowing(authUserDto.getUser_id(), token);

        HttpSession session = request.getSession(true);
        session.setAttribute("token", token);
        session.setAttribute("loginUser", authUserDto);
        session.setAttribute("followList", followList);
    }
}
