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
        // List<String> excludeUrlApi = Arrays.asList("/api/v1/auth", 
        //                                                 "/api/v1/user/login", 
        //                                                 "/api/v1/user/join");
        // List<String> excludeUrlController = Arrays.asList("/user/login",
        //                                                         "/user/join");
        // List<String> excludeUrlCommon = Arrays.asList("/",
        //                                                     "/css/**", 
        //                                                     "/error", 
        //                                                     "/*.ico");

        // excludeUrlApi.addAll(excludeUrlController);
        // excludeUrlApi.addAll(excludeUrlCommon);

        // List<String> excludeUrl = excludeUrlApi;
        
        // registry.addInterceptor(new LoginCheckInterceptor())    // 1. 인터셉터 등록
        //         .order(1)                                 // 2. 인터셉터 체인 순서 지정
        //         .addPathPatterns("/**")             // 3. 전체에 적용시키고
        //         .excludePathPatterns(excludeUrl);               // 4. 인터셉터에서 제외할 패턴 지정
    }
}
