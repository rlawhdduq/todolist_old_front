package todolist.old_front.dto.board.reply;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    private Long replyId;
    private Long boardId;
    private Long userId;
    private Long parentId;
    private String content;
    private Short replyDepth;
    private LocalDateTime createTime;
    private Character status;
    private LocalDateTime updateTime;

    public ReplyDto(Long replyId, Long boardId, Long userId, Long parentId, String content,
    Short replyDepth, LocalDateTime createTime)
    {
        this.replyId = replyId;
        this.boardId = boardId;
        this.userId = userId;
        this.parentId = parentId;
        this.content = content;
        this.replyDepth = replyDepth;
        this.createTime = createTime;
    }
}
