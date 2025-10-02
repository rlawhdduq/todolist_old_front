package todolist.service.follow;

import todolist.dto.follow.FollowDto;
import java.util.List;
import java.util.Map;

public interface FollowService {
    
    String insert(FollowDto followDto);
    String delete(FollowDto followDto);
    Map<String, List<Long>> getFollowing(Long userId);

}
