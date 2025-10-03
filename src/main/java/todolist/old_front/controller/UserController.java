package todolist.old_front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import todolist.old_front.service.user.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(path="/login", method=RequestMethod.GET)
    public String loginPage()
    {
        return "user/login";
    }

    @RequestMapping(path="/join", method=RequestMethod.GET)
    public String joinPage()
    {
        return "user/join";
    }

    @RequestMapping(path="/info", method=RequestMethod.GET)
    public String infoPage()
    {
        return "user/info";
    }

}
