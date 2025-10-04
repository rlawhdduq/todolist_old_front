package todolist.old_front.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import todolist.old_front.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        List<String> excludeUrlApi = Arrays.asList("/api/v1/auth", 
                                                        "/api/v1/user/login", 
                                                        "/api/v1/user/join");
        List<String> includeUrlApi = Arrays.asList("/api/v1/board/**", 
                                                        "/api/v1/follow/**", 
                                                        "/api/v1/user/{userId}");
        List<String> excludeUrlController = Arrays.asList("/user/login",
                                                                "/user/join");
        List<String> includeUrlController = Arrays.asList("/board/**",
                                                                "/user/info");
        List<String> excludeUrlCommon = Arrays.asList("/css/**", 
                                                            "/error", 
                                                            "/*.ico");
        List<String> includeUrlCommon = Arrays.asList();

        excludeUrlApi.addAll(excludeUrlController);
        excludeUrlApi.addAll(excludeUrlCommon);
        includeUrlApi.addAll(includeUrlController);
        includeUrlApi.addAll(includeUrlCommon);

        List<String> excludeUrl = excludeUrlApi;
        List<String> includeUrl = excludeUrlApi;
        
        registry.addInterceptor(new LoginCheckInterceptor())    // 1. 인터셉터 등록
                .order(1)                                 // 2. 인터셉터 체인 순서 지정
                .addPathPatterns(excludeUrl)                    // 3. 인터셉터를 적용할 URL 패턴 설정
                .excludePathPatterns(includeUrl);               // 4. 인터셉터에서 제외할 패턴 지정
    }
}
