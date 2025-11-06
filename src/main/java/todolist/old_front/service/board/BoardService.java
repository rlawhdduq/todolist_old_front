package todolist.old_front.service.board;

import java.util.List;

import todolist.old_front.dto.board.BoardDetailDto;
import todolist.old_front.dto.board.BoardDto;
import todolist.old_front.dto.board.BoardListDto;
import todolist.old_front.dto.board.GetBoardDto;

public interface BoardService {

    Long insert(BoardDto boardDto, String token);
    Long update(BoardDto boardDto, String token);
    String delete(Long boardId, Long userId, String token); // boardId+userId
    String detailDelete(List<Long> boardIds, Long userId, String token); // 아마 이건 다중삭제인듯?
    List<BoardListDto> getBoard(GetBoardDto getBoardDto, String token);
    List<BoardListDto> getAllBoard();
    BoardDetailDto getDetailBoard(Long boardId, String token);

}
