package todolist.old_front.dto.board;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import todolist.old_front.dto.board.todolist.TodolistDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long boardId;
    private Long userId;
    private String scopeOfDisclousre;
    private Character fulfillmentOrNot;
    private LocalDateTime createTime;
    private LocalDateTime fulfillmentTime;
    private String content;
    private Character status;
    private LocalDateTime updateTime;
    private List<TodolistDto> todlist;

    public BoardDto(Long boardId, Long userId, String scopeOfDisclousre, Character fulfillmentOrNot,
        LocalDateTime createTime, LocalDateTime fulfillmentTime, String content, LocalDateTime updateTime)
    {
        this.boardId = boardId;
        this.userId = userId;
        this.scopeOfDisclousre = scopeOfDisclousre;
        this.fulfillmentOrNot = fulfillmentOrNot;
        this.createTime = createTime;
        this.fulfillmentTime = fulfillmentTime;
        this.content = content;
        this.updateTime = updateTime;
    }
}
