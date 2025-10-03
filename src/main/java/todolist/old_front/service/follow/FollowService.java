package todolist.old_front.service.follow;

import java.util.List;
import java.util.Map;

import todolist.old_front.dto.follow.FollowDto;

public interface FollowService {
    
    String insert(FollowDto followDto);
    String delete(FollowDto followDto);
    Map<String, List<Long>> getFollowing(Long userId);

}
