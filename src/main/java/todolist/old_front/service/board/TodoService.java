package todolist.old_front.service.board;

import java.util.List;

import todolist.old_front.dto.board.todolist.TodolistDto;

public interface TodoService {

    String insert(TodolistDto todolistDto, String token);
    String update(TodolistDto todolistDto, String token);
    String delete(Long boardId, Long todoId, String token);
    String detailDelete(Long todoId, String token);
    List<TodolistDto> getTodolist(Long boardId, String token);
    
}
