package todolist.old_front.service.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import todolist.old_front.dto.board.reply.ReplyDto;
import todolist.old_front.service.board.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Value("${gateway.url}")
    private String gatewayUrl;
    @Autowired
    private WebClient webClient;

    @Override
    public String insert(ReplyDto replyDto)
    {
        String res = "등록되었습니다.";
        webClient.post()
                .uri(gatewayUrl+"/api/v1/board/reply")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(replyDto, null)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public String update(ReplyDto replyDto)
    {
        String res = "수정되었습니다.";
        webClient.put()
                .uri(gatewayUrl+"/api/v1/board/reply")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(replyDto, null)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public String delete(Long boardId, Long replyId)
    {
        String res = "삭제되었습니다.";
        webClient.delete()
                .uri(
                    uriBuilder -> uriBuilder.path(gatewayUrl+"/api/v1/board/reply")
                                            .queryParam("boardId", boardId)
                                            .queryParam("replyId", replyId)
                                            .build()
                    )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public String detailDelete(Long replyId)
    {
        String res = "삭제되었습니다.";
        webClient.delete()
                .uri(
                    uriBuilder -> uriBuilder.path(gatewayUrl+"/api/v1/board/reply")
                                            .queryParam("replyId", replyId)
                                            .build()
                    )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public List<ReplyDto> getReply(Long boardId)
    {
        List<ReplyDto> replyList = webClient.get()
                                            .uri(
                                                uriBuilder -> uriBuilder.path(gatewayUrl+"/api/v1/board/reply")
                                                                        .queryParam("boardId", boardId)
                                                                        .build()
                                                )
                                            .retrieve()
                                            .bodyToMono(new ParameterizedTypeReference<List<ReplyDto>>() {})
                                            .block();
        return replyList;
    }

    @Override
    public String deleteFromBoard(Long boardId)
    {
        String res = "수정되었습니다.";
        return res;
    }
    
    @Override
    public String detailDeleteFromBoard(List<Long> boardIds)
    {
        String res = "수정되었습니다.";
        return res;
    }
}
