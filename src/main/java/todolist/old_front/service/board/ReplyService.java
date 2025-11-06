package todolist.old_front.service.board;

import java.util.List;

import todolist.old_front.dto.board.reply.ReplyDto;

public interface ReplyService {
    
    String insert(ReplyDto replyDto, String token);
    String update(ReplyDto replyDto, String token);
    String delete(Long boardId, Long replyId, String token);
    String detailDelete(Long replyId, String token);
    List<ReplyDto> getReply(Long boardId, String token);
    String deleteFromBoard(Long boardId, String token);
    String detailDeleteFromBoard(List<Long> boardIds, String token);
    
}
