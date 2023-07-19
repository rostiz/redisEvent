package org.dadada.evele.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EveleRoom implements Serializable {
    private static final long serialVersionUID = 6494678977089006639L;
    private String roomId;
    private String name;
    private long userCount; // 채팅방 인원수
    public static EveleRoom create(String name) {
    	EveleRoom eveleRoom = new EveleRoom();
    	eveleRoom.roomId = UUID.randomUUID().toString();
    	eveleRoom.name = name;
        return eveleRoom;
    }
}