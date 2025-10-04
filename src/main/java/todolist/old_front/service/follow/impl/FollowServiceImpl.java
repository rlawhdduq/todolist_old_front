package todolist.old_front.service.follow.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import todolist.old_front.dto.follow.FollowDto;
import todolist.old_front.service.follow.FollowService;

@Service
public class FollowServiceImpl implements FollowService{

    @Value("${gateway.url}")
    private String gatewayUrl;
    @Autowired
    private WebClient webClient;

    @Override
    public String insert(FollowDto followDto)
    {
        String res = "등록되었습니다.";
        webClient.post()
                .uri(gatewayUrl+"/api/v1/follow")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(followDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public String delete(FollowDto followDto)
    {
        String res = "삭제되었습니다.";
        webClient.put()
                .uri(
                    uriBuilder -> uriBuilder.path(gatewayUrl+"/api/v1/follow")
                                            .queryParam("following_user_id", followDto.getFollowing_user_id())
                                            .queryParam("follower_user_id", followDto.getFollower_user_id())
                                            .build()
                    )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;
    }

    @Override
    public Map<String, List<Long>> getFollowing(Long userId)
    {
        Map<String, List<Long>> followerList = webClient.get()
                                            .uri(
                                                uriBuilder -> uriBuilder.path(gatewayUrl+"/api/v1/folow")
                                                                        .queryParam("userId", userId)
                                                                        .build()
                                                )
                                            .retrieve()
                                            .bodyToMono(new ParameterizedTypeReference<Map<String, List<Long>>>() {})
                                            .block();
        return followerList;
    }
}
