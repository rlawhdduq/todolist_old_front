package todolist.old_front.service.board;

import java.util.List;

import todolist.old_front.dto.board.todolist.TodolistDto;

public interface TodoService {

    String insert(TodolistDto todolistDto);
    String update(TodolistDto todolistDto);
    String delete(Long boardId, Long todoId);
    String detailDelete(Long todoId);
    List<TodolistDto> getTodolist(Long boardId);
    
}
