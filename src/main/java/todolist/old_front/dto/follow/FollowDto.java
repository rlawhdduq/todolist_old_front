package todolist.old_front.dto.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {
    private Long following_user_id;
    private Long follower_user_id;
}
