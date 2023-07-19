/*package org.dadada.evele.pubsub;

import org.dadada.evele.dto.EveleMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EveleRedisPublisher {
    private final RedisTemplate<String, Object> redisTemplate;
    public void publish(ChannelTopic topic, EveleMessage message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}*/