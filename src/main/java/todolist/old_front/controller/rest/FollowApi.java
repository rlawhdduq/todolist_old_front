package todolist.old_front.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import todolist.old_front.dto.follow.FollowDto;
import todolist.old_front.service.follow.impl.FollowServiceImpl;


@RestController
@RequestMapping("/api/v1/follow")
public class FollowApi {

    @Autowired
    private FollowServiceImpl followService;

    @RequestMapping(path="/{userId}", method=RequestMethod.GET)
    public Map<String, List<Long>> requestMethodName(@PathVariable Long userId)
    {
        Map<String, List<Long>> res = followService.getFollowing(userId);
        return res;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String insertFollow(FollowDto followDto)
    {
        String res = followService.insert(followDto);
        return res;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public String deleteFollow(FollowDto followDto)
    {
        String res = followService.delete(followDto);
        return res;
    }

}
