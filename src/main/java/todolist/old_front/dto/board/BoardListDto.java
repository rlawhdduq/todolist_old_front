package todolist.old_front.dto.board;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDto {

    private Long boardId;
    private Long userId;
    private String scopeOfDisclosure;
    private LocalDateTime createTime;
    private LocalDateTime fulfillmentTime;
    private String content;
    private LocalDateTime updateTime;

    public BoardListDto(Long boardId, Long userId, String scopeOfDisclosure, 
        LocalDateTime createTime, LocalDateTime fulfillmentTime, String content)
    {
        this.boardId = boardId;
        this.userId = userId;
        this.scopeOfDisclosure = scopeOfDisclosure;
        this.createTime = createTime;
        this.fulfillmentTime = fulfillmentTime;
        this.content = content;
    }

}
