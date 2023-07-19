package org.dadada.evele.service;

import org.dadada.evele.dto.EveleMessage;
import org.dadada.evele.repo.EveleRoomRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EveleService {
    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final EveleRoomRepository eveleRoomRepository;
    /**
     * destination정보에서 roomId 추출
     */
    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }
    /**
     * 채팅방에 메시지 발송
     */
    public void sendEveleMessage(EveleMessage eveleMessage) {
        eveleMessage.setUserCount(eveleRoomRepository.getUserCount(eveleMessage.getRoomId()));
        if (EveleMessage.MessageType.ENTER.equals(eveleMessage.getType())) {
        	eveleMessage.setMessage(eveleMessage.getSender() + "님이 방에 입장했습니다.");
        	eveleMessage.setSender("[알림]");
        } else if (EveleMessage.MessageType.QUIT.equals(eveleMessage.getType())) {
        	eveleMessage.setMessage(eveleMessage.getSender() + "님이 방에서 나갔습니다.");
        	eveleMessage.setSender("[알림]");
        }
        redisTemplate.convertAndSend(channelTopic.getTopic(), eveleMessage);
    }
}