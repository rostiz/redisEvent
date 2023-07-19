/*package org.dadada.evele.handler;

import org.dadada.evele.domain.EveleRoom;
import org.dadada.evele.dto.EveleMessage;
import org.dadada.evele.service.EveleService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class EveleHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final EveleService eveleService;
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        

        EveleMessage eveleMessage = objectMapper.readValue(payload, EveleMessage.class);
        EveleRoom room = eveleService.findRoomById(eveleMessage.getRoomId());
        room.handleActions(session, eveleMessage, eveleService);
        
    }
}*/