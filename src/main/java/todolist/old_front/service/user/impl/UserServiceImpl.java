package todolist.old_front.service.user.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import todolist.old_front.dto.user.AuthUserDto;
import todolist.old_front.dto.user.JoinUserDto;
import todolist.old_front.dto.user.LoginUserDto;
import todolist.old_front.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Value("${gateway.url}")
    private String gatewayUrl;

    private WebClient webClient;

    @Override
    public AuthUserDto getUser(Long userId)
    {
        AuthUserDto callRes = webClient.get()
                                 .uri(gatewayUrl+"/"+userId)
                                 .retrieve()
                                 .bodyToMono(AuthUserDto.class).block();
        return callRes;
    }
    
    @Override
    public AuthUserDto joinUser(JoinUserDto joinUserDto)
    {
        AuthUserDto callRes = webClient.post()
                                .uri(gatewayUrl+"/api/v1/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(joinUserDto, null)
                                .retrieve()
                                .bodyToMono(AuthUserDto.class).block();
        return callRes;
    }

    @Override
    public AuthUserDto loginUser(LoginUserDto loginUserDto)
    {
        AuthUserDto callRes = webClient.post()
                                .uri(gatewayUrl+"/api/v1/user/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(loginUserDto, null)
                                .retrieve()
                                .bodyToMono(AuthUserDto.class).block();
        return callRes;
    }
}
