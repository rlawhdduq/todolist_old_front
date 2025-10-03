package todolist.old_front.service.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import todolist.old_front.dto.board.todolist.TodolistDto;
import todolist.old_front.service.board.TodoService;

@Service
public class TodoServiceImpl implements TodoService{

    @Value("${gateway.url}")
    private String gatewayUrl;

    private WebClient webClient;

    @Override
    public String insert(TodolistDto todolistDto)
    {
        String res = "등록되었습니다.";
        webClient.post()
                .uri(gatewayUrl+"/api/v1/board/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(todolistDto, null)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public String update(TodolistDto todolistDto)
    {
        String res = "수정되었습니다.";
        webClient.put()
                .uri(gatewayUrl+"/api/v1/board/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(todolistDto, null)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public String delete(Long boardId, Long todoId)
    {
        String res = "삭제되었습니다.";
        webClient.delete()
                .uri(
                    uriBuilder -> uriBuilder.path(gatewayUrl+"/api/v1/board/todo")
                                            .queryParam("boardId", boardId)
                                            .queryParam("todoId", todoId)
                                            .build()
                    )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public String detailDelete(Long todoId)
    {
        String res = "삭제되었습니다.";
        webClient.delete()
                .uri(
                    uriBuilder -> uriBuilder.path(gatewayUrl+"/api/v1/board/todo")
                                            .queryParam("todoId", todoId)
                                            .build()
                    )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public List<TodolistDto> getTodolist(Long boardId)
    {
        List<TodolistDto> todolist = webClient.get()
                                            .uri(
                                                uriBuilder -> uriBuilder.path(gatewayUrl+"/api/v1/board/todo")
                                                                        .queryParam("boardId", boardId)
                                                                        .build()
                                                )
                                            .retrieve()
                                            .bodyToMono(new ParameterizedTypeReference<List<TodolistDto>>() {})
                                            .block();
        return todolist;
    }
}
