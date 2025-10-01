package todolist.service.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import todolist.service.board.TodoService;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private WebClient webClient;
    
    @Value("${gateway.url}")
    private String gatewayUrl;
}
