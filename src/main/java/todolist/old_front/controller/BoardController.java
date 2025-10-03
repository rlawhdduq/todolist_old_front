package todolist.old_front.controller
;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import todolist.old_front.dto.board.BoardDetailDto;
import todolist.old_front.dto.board.BoardListDto;
import todolist.old_front.dto.board.GetBoardDto;
import todolist.old_front.dto.board.reply.ReplyDto;
import todolist.old_front.dto.board.todolist.TodolistDto;
import todolist.old_front.service.board.impl.BoardServiceImpl;
import todolist.old_front.service.board.impl.ReplyServiceImpl;
import todolist.old_front.service.board.impl.TodoServiceImpl;

import org.springframework.web.bind.annotation.RequestMethod;


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
    public String getBoardList(GetBoardDto getBoardDto)
    {
       List<BoardListDto> boardList = boardService.getBoard(getBoardDto);
       return "board/board";
    }
    
    // 게시글
    @RequestMapping(path="/{boardId}", method=RequestMethod.GET)
    public String getBoardDetail(@PathVariable Long boardId)
    {
        BoardDetailDto detailBoard = boardService.getDetailBoard(boardId);
        List<ReplyDto> replyList = replyService.getReply(boardId);
        List<TodolistDto> todolist = todoService.getTodolist(boardId);

        detailBoard.setReply(replyList);
        detailBoard.setTodolist(todolist);

        return "board/boardDetail";
    }
    
    // Reply
    // Todo
}
