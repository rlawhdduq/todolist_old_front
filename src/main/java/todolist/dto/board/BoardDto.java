package todolist.dto.board;

import java.time.LocalDateTime;
import java.util.List;

import todolist.dto.board.todolist.TodolistDto;

public class BoardDto {
    private Long board_id;
    private Long user_id;
    private String scope_of_disclousre;
    private Character fulfillment_or_not;
    private LocalDateTime create_time;
    private LocalDateTime fulfillment_time;
    private String content;
    private Character status;
    private LocalDateTime update_time;
    private List<TodolistDto> todlist;

    public BoardDto(Long board_id, Long user_id, String scope_of_disclosure, Character fulfillment_or_not,
        LocalDateTime create_time, LocalDateTime fulfillment_time, String content, LocalDateTime update_time)
    {
        this.board_id = board_id;
        this.user_id = user_id;
        this.scope_of_disclousre = scope_of_disclosure;
        this.fulfillment_or_not = fulfillment_or_not;
        this.create_time = create_time;
        this.fulfillment_time = fulfillment_time;
        this.content = content;
        this.update_time = update_time;
    }
}
