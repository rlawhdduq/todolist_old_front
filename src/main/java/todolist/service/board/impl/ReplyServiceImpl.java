package todolist.service.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import todolist.service.board.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private WebClient webClient;
    
    @Value("${gateway.url}")
    private String gatewayUrl;
}
