package todolist.old_front.service.board.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import todolist.old_front.dto.board.BoardDetailDto;
import todolist.old_front.dto.board.BoardDto;
import todolist.old_front.dto.board.BoardListDto;
import todolist.old_front.dto.board.GetBoardDto;
import todolist.old_front.service.board.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

    @Value("${gateway.url}")
    private String gatewayUrl;
    @Autowired
    private WebClient webClient;
    
    @Override
    public Long insert(BoardDto boardDto, String token)
    {
        Long callRes = webClient.post()
                                .uri(gatewayUrl+"/api/v1/board")
                                .header("token", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(boardDto)
                                .retrieve()
                                .bodyToMono(Long.class)
                                .block();
        return callRes;
    }

    @Override
    public Long update(BoardDto boardDto, String token)
    {
        Long callRes = webClient.put()
                                .uri(gatewayUrl+"/api/v1/board")
                                .header("token", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(boardDto)
                                .retrieve()
                                .bodyToMono(Long.class)
                                .block();
        return callRes;
    }

    @Override
    public String delete(Long boardId, Long userId, String token)
    {
        String res = "삭제되었습니다.";
        webClient.delete()
                .uri(
                    uriBuilder -> uriBuilder.path(gatewayUrl+"/api/v1/board")
                                            .queryParam("boardId", boardId)
                                            .queryParam("userId", userId)
                                            .build()
                    )
                .header("token", token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public String detailDelete(List<Long> boardIds, Long userId, String token)
    {
        String res = "삭제되었습니다.";
        webClient.delete()
                .uri(gatewayUrl+"/api/v1/board", uriBuilder -> uriBuilder
                                            .queryParam("boardIds", boardIds)
                                            .queryParam("userId", userId)
                                            .build()
                    )
                .header("token", token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public List<BoardListDto> getBoard(Long userId, String token)
    {
        List<BoardListDto> boardList = webClient.get()
                                                .uri(gatewayUrl+"/api/v1/board/"+userId)
                                                .header("token", token)
                                                .retrieve()
                                                .bodyToMono(new ParameterizedTypeReference<List<BoardListDto>>() {})
                                                .block();
        return boardList;
    }

    @Override
    public List<BoardListDto> getAllBoard()
    {
        List<BoardListDto> boardList = webClient.get()
                                                .uri(gatewayUrl+"/api/v1//all")
                                                .retrieve()
                                                .bodyToMono(new ParameterizedTypeReference<List<BoardListDto>>() {})
                                                .block();
        return boardList;
    }

    @Override
    public BoardDetailDto getDetailBoard(Long boardId, String token)
    {
        BoardDetailDto detailBoard = webClient.get()
                                            .uri(gatewayUrl+"/api/v1/board/detail",  uriBuilder-> uriBuilder.path("/{boardId}").build(boardId))
                                            .header("token", token)
                                            .retrieve()
                                            .bodyToMono(BoardDetailDto.class)
                                            .block();
        return detailBoard;
    }

}
