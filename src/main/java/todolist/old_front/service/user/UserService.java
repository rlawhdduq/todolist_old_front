package todolist.old_front.service.user;


import todolist.old_front.dto.user.AuthUserDto;
import todolist.old_front.dto.user.JoinUserDto;
import todolist.old_front.dto.user.LoginUserDto;

public interface UserService {
    AuthUserDto getUser(String id);
    AuthUserDto joinUser(JoinUserDto joinUserDto);
    AuthUserDto loginUser(LoginUserDto loginUserDto);
}
