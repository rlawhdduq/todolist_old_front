package todolist.old_front.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import todolist.old_front.dto.board.BoardDetailDto;
import todolist.old_front.dto.board.BoardDto;
import todolist.old_front.dto.board.reply.ReplyDto;
import todolist.old_front.dto.user.AuthUserDto;
import todolist.old_front.service.board.impl.BoardServiceImpl;
import todolist.old_front.service.board.impl.ReplyServiceImpl;
import todolist.old_front.service.board.impl.TodoServiceImpl;

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
    public BoardDetailDto getBoardDetail(@PathVariable Long boardId, HttpSession session)
    {
        BoardDetailDto detailBoard = boardService.getDetailBoard(boardId, session.getAttribute("token").toString());
        return detailBoard;
    }
    
    @RequestMapping(path="/board", method=RequestMethod.POST)
    public Long insertBoard(BoardDto boardDto, HttpSession session) {
        AuthUserDto authUser = (AuthUserDto) session.getAttribute("loginUser");
        boardDto.setUser_id(authUser.getUser_id());
        boardDto.setWriter_id(authUser.getId());
        Long board_id = boardService.insert(boardDto, session.getAttribute("token").toString());
        return board_id;
    }
    @RequestMapping(path="/board", method=RequestMethod.PUT)
    public void updateBoard(BoardDto boardDto, HttpSession session)
    {
        boardService.update(boardDto, session.getAttribute("token").toString());
        return;
    }
    @RequestMapping(path="/board", method=RequestMethod.DELETE)
    public void deleteBoard(@RequestParam Long boardId, Long userId, HttpSession session)
    {
        boardService.delete(boardId, userId, session.getAttribute("token").toString());
        return;
    }
    @RequestMapping(path="/board/detail", method=RequestMethod.DELETE)
    public void detailDeleteBoard(@RequestParam List<Long> boardIds, Long userId, HttpSession session)
    {
        boardService.detailDelete(boardIds, userId, session.getAttribute("token").toString());
        return;
    }
    
    // Reply
    @RequestMapping(path="/reply", method=RequestMethod.POST)
    public String insertReply(ReplyDto replyDto, HttpSession session)
    {
        String res = "등록되었습니다.";
        replyService.insert(replyDto, session.getAttribute("token").toString());
        return res;
    }

    @RequestMapping(path="/reply", method=RequestMethod.PUT)
    public String updateReply(ReplyDto replyDto, HttpSession session)
    {
        String res = "수정되었습니다.";
        replyService.update(replyDto, session.getAttribute("token").toString());
        return res;
    }
    // Todo
    // Todo는 따로 안해도 될듯...?
}
