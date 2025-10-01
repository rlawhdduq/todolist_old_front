package todolist.service.auth;

import todolist.dto.auth.AuthInfoDto;

public interface AuthService {
    String getToken(AuthInfoDto authInfoDto);
}
