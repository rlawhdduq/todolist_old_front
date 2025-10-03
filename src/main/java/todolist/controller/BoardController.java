package todolist.controller
;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import todolist.dto.board.BoardDetailDto;
import todolist.dto.board.BoardListDto;
import todolist.dto.board.GetBoardDto;
import todolist.dto.board.reply.ReplyDto;
import todolist.dto.board.todolist.TodolistDto;
import todolist.service.board.impl.BoardServiceImpl;
import todolist.service.board.impl.ReplyServiceImpl;
import todolist.service.board.impl.TodoServiceImpl;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;
    @Autowired
    private ReplyServiceImpl replyService;
    @Autowired
    private TodoServiceImpl todoService;

    // 게시판
    @RequestMapping(method=RequestMethod.GET)
    public List<BoardListDto> getBoardList(@RequestParam GetBoardDto getBoardDto)
    {
       List<BoardListDto> boardList = boardService.getBoard(getBoardDto);
       return boardList;
    }
    
    // 게시글
    @RequestMapping(path="/{boardId}", method=RequestMethod.GET)
    public BoardDetailDto getBoardDetail(@PathVariable Long boardId)
    {
        BoardDetailDto detailBoard = boardService.getDetailBoard(boardId);
        List<ReplyDto> replyList = replyService.getReply(boardId);
        List<TodolistDto> todolist = todoService.getTodolist(boardId);

        detailBoard.setReply(replyList);
        detailBoard.setTodolist(todolist);

        return detailBoard;
    }
    
    // Reply
    // Todo
}
