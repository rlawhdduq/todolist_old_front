package todolist.service.board;

import java.util.List;

import todolist.dto.board.BoardDetailDto;
import todolist.dto.board.BoardDto;
import todolist.dto.board.BoardListDto;
import todolist.dto.board.GetBoardDto;

public interface BoardService {

    Long insert(BoardDto boardDto);
    Long update(BoardDto boardDto);
    void delete(Long boardId, Long userId); // boardId+userId
    void detailDelete(List<Long> boardIds, Long userId); // 아마 이건 다중삭제인듯?
    List<BoardListDto> getBoard(GetBoardDto getBoardDto);
    BoardDetailDto getDetailBoard(Long boardId);

}
