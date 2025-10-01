package todolist.service.user;

import todolist.dto.user.AuthUserDto;
import todolist.dto.user.JoinUserDto;
import todolist.dto.user.LoginUserDto;

public interface UserService {
    AuthUserDto getUser(Long userId);
    AuthUserDto joinUser(JoinUserDto joinUserDto);
    AuthUserDto loginUser(LoginUserDto loginUserDto);
}
