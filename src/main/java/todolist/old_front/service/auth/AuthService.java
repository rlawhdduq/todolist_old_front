package todolist.old_front.service.auth;

import todolist.old_front.dto.auth.AuthInfoDto;

public interface AuthService {
    String getToken(AuthInfoDto authInfoDto);
}
