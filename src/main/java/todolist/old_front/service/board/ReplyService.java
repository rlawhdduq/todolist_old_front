package todolist.old_front.service.board;

import java.util.List;

import todolist.old_front.dto.board.reply.ReplyDto;

public interface ReplyService {
    
    String insert(ReplyDto replyDto);
    String update(ReplyDto replyDto);
    String delete(Long boardId, Long replyId);
    String detailDelete(Long replyId);
    List<ReplyDto> getReply(Long boardId);
    String deleteFromBoard(Long boardId);
    String detailDeleteFromBoard(List<Long> boardIds);
    
}
