package todolist.old_front.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todolist.old_front.dto.follow.FollowDto;
import todolist.old_front.service.follow.impl.FollowServiceImpl;

@RestController
@RequestMapping("/api/v1/follow")
public class FollowApi {

    @Autowired
    private FollowServiceImpl followService;

    @RequestMapping()
    public String insertFollow(FollowDto followDto)
    {
        
        return "";
    }

    @RequestMapping()
    public String deleteFollow(FollowDto followDto)
    {
        return "";
    }

}
