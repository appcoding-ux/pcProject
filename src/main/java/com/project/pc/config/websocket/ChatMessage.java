package com.project.pc.config.websocket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessage {

    // 메시지 타입 : 입장, 채팅, 나감
    public enum MessageType {
        ENTER, TALK, OUT, ADMIN_ENTER
    }

    private MessageType type; // 메시지 타입

    private String roomId; // 방번호

    private String sender; // 메시지 보낸사람

    private String message; // 메시지 내용
}
