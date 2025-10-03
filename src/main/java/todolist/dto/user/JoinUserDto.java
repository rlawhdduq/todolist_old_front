package todolist.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinUserDto {
    
    private String id;
    private String password;
    private String addr;
    private String addr_dt;
    private String birth;
    private String ph;
    private Character gender;
    
}
