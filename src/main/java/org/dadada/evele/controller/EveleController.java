package org.dadada.evele.controller;

import org.dadada.evele.dto.EveleMessage;
import org.dadada.evele.repo.EveleRoomRepository;
import org.dadada.evele.service.EveleService;
import org.dadada.evele.service.JwtTokenProvider;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
	public class EveleController {

    private final EveleRoomRepository eveleRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final EveleService eveleService;
    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(EveleMessage message, @Header("token") String token) {
        String nickname = jwtTokenProvider.getUserNameFromJwt(token);
        // 로그인 회원 정보로 대화명 설정
        message.setSender(nickname);
        // 채팅방 인원수 세팅
        message.setUserCount(eveleRoomRepository.getUserCount(message.getRoomId()));
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        eveleService.sendEveleMessage(message);
    }
}