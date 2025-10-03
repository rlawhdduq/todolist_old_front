package todolist.controller.rest;

import org.springframework.web.bind.annotation.RestController;

import todolist.dto.auth.AuthInfoDto;
import todolist.service.auth.impl.AuthServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {

    @Autowired
    private AuthServiceImpl authService;

    @RequestMapping(method=RequestMethod.POST)
    public String getToken(@RequestBody AuthInfoDto authInfoDto)
    {
        String token = authService.getToken(authInfoDto);
        return token;
    }
    
}
