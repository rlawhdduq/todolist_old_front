package todolist.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todolist.dto.board.BoardDetailDto;
import todolist.dto.board.BoardDto;
import todolist.dto.board.reply.ReplyDto;
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
    @RequestMapping(path="/board/detail/{boardId}", method=RequestMethod.GET)
    public BoardDetailDto getBoardDetail(@PathVariable Long boardId)
    {
        BoardDetailDto detailBoard = boardService.getDetailBoard(boardId);
        return detailBoard;
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
    @RequestMapping(path="/reply", method=RequestMethod.POST)
    public String insertReply(ReplyDto replyDto)
    {
        String res = "등록되었습니다.";
        replyService.insert(replyDto);
        return res;
    }

    @RequestMapping(path="/reply", method=RequestMethod.PUT)
    public String updateReply(ReplyDto replyDto)
    {
        String res = "수정되었습니다.";
        replyService.update(replyDto);
        return res;
    }
    // Todo
    // Todo는 따로 안해도 될듯...?
}
