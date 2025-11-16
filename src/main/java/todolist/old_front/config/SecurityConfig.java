package todolist.old_front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(auth -> 
            auth.requestMatchers(
                // Api
                "/api/v1/auth",
                "/api/v1/user/login",
                "/api/v1/user/join",
                "/api/v1/user/logout",
                // Controller(View)
                "/",
                "/user/login",
                "/user/join",
                // Css/js
                "/js/**",
                "/css/**",
                "/error",
                "/*.ico"
            ).permitAll()
            // 그 외에는 전부 인증 필요
            .anyRequest().authenticated()
        )
        .formLogin(form -> 
            form.loginPage("/user/login")
            .defaultSuccessUrl("/", true)
            .permitAll()
        )
        .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
