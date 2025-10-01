package todolist.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import todolist.dto.user.AuthUserDto;
import todolist.dto.user.JoinUserDto;
import todolist.dto.user.LoginUserDto;
import todolist.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private WebClient webClient;
    
    @Value("${gateway.url}")
    private String gatewayUrl;

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
