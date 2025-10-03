package todolist.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfoDto {
    private Long userId;
    private String id;
    private String userType;
}
