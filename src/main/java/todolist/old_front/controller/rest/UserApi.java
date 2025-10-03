package todolist.old_front.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todolist.old_front.dto.user.AuthUserDto;
import todolist.old_front.dto.user.JoinUserDto;
import todolist.old_front.service.user.impl.UserServiceImpl;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/v1/user")
public class UserApi {

    @Autowired
    private UserServiceImpl userService;
    
    @RequestMapping(method=RequestMethod.POST)
    public AuthUserDto joinUser(@RequestBody JoinUserDto joinUserDto)
    {
        AuthUserDto authUserDto = userService.joinUser(joinUserDto);
        return authUserDto;
    }
    
}
