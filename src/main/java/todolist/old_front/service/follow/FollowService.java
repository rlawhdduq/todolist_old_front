package todolist.old_front.service.follow;

import java.util.List;
import java.util.Map;

import todolist.old_front.dto.follow.FollowDto;

public interface FollowService {
    
    String insert(FollowDto followDto, String token);
    String delete(FollowDto followDto, String token);
    Map<String, List<Long>> getFollowing(Long userId, String token);
    Boolean followState(Long target_user_id, Long source_user_id, String token);
}
