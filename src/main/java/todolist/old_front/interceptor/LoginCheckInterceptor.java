package todolist.old_front.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception
    {
        String requestUri = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestUri);

        HttpSession session = request.getSession(false); // 세션이 없으면 null 반환

        if( session == null || session.getAttribute("loginUser") == null )
        {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/");
            return false; // 호출 차단
        }

        return true; // 정상이면 계속 진행
    }
}
