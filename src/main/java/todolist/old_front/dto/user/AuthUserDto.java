package todolist.old_front.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthUserDto {

    private Long user_id;
    private String id;
    private String user_type;
    private Long number_of_following;
    private Long number_of_follower;
    
}
