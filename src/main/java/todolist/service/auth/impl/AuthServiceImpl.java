package todolist.service.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import todolist.dto.auth.AuthInfoDto;
import todolist.service.auth.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private WebClient webClient;
    
    @Value("${gateway.url}")
    private String gatewayUrl;
    
    @Override
    public String getToken(AuthInfoDto authInfoDto)
    {
        String callRes = webClient.post()
                                .uri(gatewayUrl+"/api/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(authInfoDto, null)
                                .retrieve()
                                .bodyToMono(String.class).block();
        return callRes;
    }
}
