package todolist.old_front.dto.board.todolist;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodolistDto {
    private Long todolistId;
    private Long boardId;
    private LocalDateTime createTime;
    private String todoType;
    private String todoTypeDetail;
    private String todoUnit;
    private Short todoNumber;
    private Character fulfillmentOrNot;
    private Character status;
    private LocalDateTime updateTime;

    public TodolistDto(Long todolistId, Long boardId, LocalDateTime createTime, String todoType,
        String todoTypeDetail, String todoUnit, Short todoNumber, Character fulfillmentOrNot)
    {
        this.todolistId = todolistId;
        this.boardId = boardId;
        this.createTime = createTime;
        this.todoType = todoType;
        this.todoTypeDetail = todoTypeDetail;
        this.todoUnit = todoUnit;
        this.todoNumber = todoNumber;
        this.fulfillmentOrNot = fulfillmentOrNot;
    }
}
