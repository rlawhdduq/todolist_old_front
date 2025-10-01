package todolist.controller.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todolist.dto.board.BoardDto;
import todolist.dto.board.BoardListDto;
import todolist.dto.board.DeleteDto;
import todolist.dto.board.DetailDeleteDto;
import todolist.dto.board.GetBoardDto;
import todolist.service.board.BoardService;
import todolist.service.board.impl.BoardServiceImpl;
import todolist.service.board.impl.ReplyServiceImpl;
import todolist.service.board.impl.TodoServiceImpl;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1")
public class BoardApi {

    @Autowired
    private BoardServiceImpl boardService;
    @Autowired
    private ReplyServiceImpl replyService;
    @Autowired
    private TodoServiceImpl todoServie;

    // Board
    @RequestMapping(path="/board", method=RequestMethod.GET)
    public List<BoardListDto> getBoard(@RequestParam GetBoardDto getBoardDto)
    {
       List<BoardListDto> boardList = boardService.getBoard(getBoardDto);
       return boardList;
    }
    
    @RequestMapping(path="/board/detail/{boardId}", method=RequestMethod.GET)
    public String getBoardDetail(@PathVariable Long boardId)
    {
        return new String();
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public void insertBoard(@RequestBody BoardDto boardDto) {
        boardService.insert(boardDto);
        return;
    }
    @RequestMapping(method=RequestMethod.PUT)
    public void updateBoard(@RequestBody BoardDto boardDto)
    {
        boardService.update(boardDto);
        return;
    }
    @RequestMapping(method=RequestMethod.DELETE)
    public void deleteBoard(@RequestParam Long boardId, Long userId)
    {
        boardService.delete(boardId, userId);
        return;
    }
    @RequestMapping(path="/detail", method=RequestMethod.DELETE)
    public void detailDeleteBoard(@RequestParam List<Long> boardIds, Long userId)
    {
        boardService.detailDelete(boardIds, userId);
        return;
    }
    
    // Reply
    // Todo
}
