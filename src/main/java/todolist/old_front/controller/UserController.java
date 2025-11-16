package todolist.old_front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import todolist.old_front.dto.user.JoinUserDto;
import todolist.old_front.dto.user.LoginUserDto;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(path="/login", method=RequestMethod.GET)
    public String loginPage(Model model)
    {
        model.addAttribute("LoginUserDto", new LoginUserDto());
        return "user/login";
    }

    @RequestMapping(path="/join", method=RequestMethod.GET)
    public String joinPage(Model model)
    {
        model.addAttribute("JoinUserDto", new JoinUserDto());
        return "user/join";
    }

    @RequestMapping(path="/info", method=RequestMethod.GET)
    public String infoPage()
    {
        return "user/info";
    }

}
