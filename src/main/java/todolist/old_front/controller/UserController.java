package todolist.old_front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import todolist.old_front.dto.user.AuthUserDto;
import todolist.old_front.dto.user.JoinUserDto;
import todolist.old_front.dto.user.LoginUserDto;
import todolist.old_front.service.follow.impl.FollowServiceImpl;
import todolist.old_front.service.user.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    FollowServiceImpl followService;

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

    @RequestMapping(path="/info/{id}", method=RequestMethod.GET)
    public String infoPage(@PathVariable String id, Model model, HttpSession session)
    {
        // 파라미터로 넘어온 id와 현재 세션에 저장된 AuthUserDto에 있는 id를 비교, 자기자신인지 판별한다
        AuthUserDto userInfo = (AuthUserDto) session.getAttribute("loginUser");
        AuthUserDto targetInfo = userService.getUser(id);
        Boolean isMyself = true;
        Boolean isFollowing = true;
        if(!userInfo.getId().equals(targetInfo.getId()))
        {
            // 나아님
            isMyself = false;
            isFollowing = followService.followState(targetInfo.getUser_id(), userInfo.getUser_id(), session.getAttribute("token").toString());
        }
        model.addAttribute("isFollowing", isFollowing);
        model.addAttribute("isMyself", isMyself);
        model.addAttribute("targetInfo", targetInfo);
        return "user/info";
    }

}
