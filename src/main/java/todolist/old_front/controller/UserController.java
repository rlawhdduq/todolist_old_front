package todolist.old_front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import todolist.old_front.dto.user.AuthUserDto;
import todolist.old_front.dto.user.LoginUserDto;
import todolist.old_front.service.user.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(path="/{userId}", method=RequestMethod.GET)
    public AuthUserDto getUser(@PathVariable Long userId)
    {
        AuthUserDto authUserDto = userService.getUser(userId);
        return authUserDto;
    }

    @RequestMapping(path="/login", method=RequestMethod.POST)
    public AuthUserDto loginUser(@RequestBody LoginUserDto loginUserDto)
    {
        AuthUserDto authUserDto = userService.loginUser(loginUserDto);
        return authUserDto;
    }

}
