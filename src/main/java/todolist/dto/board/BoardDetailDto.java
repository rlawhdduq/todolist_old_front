package todolist.dto.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import todolist.dto.board.todolist.TodolistDto;
import todolist.dto.board.reply.ReplyDto;

    
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDto {

    private Long boardId;
    private Long userId;
    private String scopeOfDisclosure;
    private Character fulfillmentOrNot;
    private LocalDateTime createTime;
    private LocalDateTime fulfillmentTime;
    private String content;
    private LocalDateTime updateTime;
    private List<TodolistDto> todolist;
    private List<ReplyDto> reply;

    public BoardDetailDto(Long boardId, Long userId, String scopeOfDisclosure, 
    Character fulfillmentOrNot, LocalDateTime createTime, LocalDateTime fulfillmentTime, String content)
    {
        this.boardId = boardId;
        this.userId = userId;
        this.scopeOfDisclosure = scopeOfDisclosure;
        this.fulfillmentOrNot = fulfillmentOrNot;
        this.createTime = createTime;
        this.fulfillmentTime = fulfillmentTime;
        this.content = content;
    }

    public void setTodolist(List<TodolistDto> todolist)
    {
        if( todolist == null )
        {
            this.todolist = new ArrayList<>();
        }
        else
        {
            this.todolist = todolist;
        }
    }
    public void setReply(List<ReplyDto> reply)
    {
        if( reply == null )
        {
            this.reply = new ArrayList<>();
        }
        else
        {
            this.reply = reply;
        }
    }

}
