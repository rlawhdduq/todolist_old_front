package todolist.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todolist.dto.user.AuthUserDto;
import todolist.dto.user.JoinUserDto;
import todolist.dto.user.LoginUserDto;
import todolist.service.user.impl.UserServiceImpl;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/v1/user")
public class UserApi {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(path="/{userId}", method=RequestMethod.GET)
    public AuthUserDto getUser(@PathVariable Long userId)
    {
        AuthUserDto authUserDto = userService.getUser(userId);
        return authUserDto;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public AuthUserDto joinUser(@RequestBody JoinUserDto joinUserDto)
    {
        AuthUserDto authUserDto = userService.joinUser(joinUserDto);
        return authUserDto;
    }
    
    @RequestMapping(path="/login", method=RequestMethod.POST)
    public AuthUserDto loginUser(@RequestBody LoginUserDto loginUserDto)
    {
        AuthUserDto authUserDto = userService.loginUser(loginUserDto);
        return authUserDto;
    }
}
