package com.project.pc.config.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pc.domain.Member;
import com.project.pc.domain.MemberRole;
import com.project.pc.dto.MemberDTO;
import com.project.pc.security.dto.MemberSecurityDTO;
import com.project.pc.service.chat.ChatService;
import com.project.pc.service.kakaoTalk.MessageService;
import com.project.pc.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;

@Component
@Log4j2
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;

    private final ChatService chatService;

    private final MessageService messageService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

        System.out.println(chatMessage);

        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        Set<WebSocketSession> sessions = room.getSessions(); // 방에 있는 현재 사용자 한명이 WebSocketSession

        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            //사용자가 방에 입장하면 Enter 메세지를 보내도록 해놓는다. => 이건 새로운 사용자가 socket 연결한 것이랑은 다름.
            // socket연결은 이 메세지 보내기전에 이미 되어있는 상태
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");

            // 나에게 카카오톡 보내기 (관리자가 카카오로 로그인 했을 경우)
            // 나중에 여기서 혹시나 카톡을 못볼 경우를 대비해 이메일도 보낼 수 있다.
            // 근데 이게 카카오 로그인한 사람에게 메세지가 보내지는 거라서...
            /*일단은 관리자가 카카오 로그인을 해야하기 때문에 비활성화*/
//            if(!chatMessage.getSender().equals("admin")){
//                messageService.sendMessage();
//            }

            sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatMessage)));
        }else if(chatMessage.getType().equals(ChatMessage.MessageType.ADMIN_ENTER)){
            // 관리자가 채팅방에 접속했을 때
            sessions.add(session);
            sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatMessage)));
        }else if(chatMessage.getType().equals(ChatMessage.MessageType.OUT)){
            sessions.remove(session);
            chatService.removeRoom(room.getRoomId());
            chatMessage.setMessage(chatMessage.getSender() + "님이 퇴장했습니다..");
            sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatMessage)));
        }else {
            sendToEachSocket(sessions, message); // 입장, 퇴장이 아닐 때는 클라이언트로부터 온 메세지 그대로 전달
        }
    }

    private void sendToEachSocket(Set<WebSocketSession> sessions, TextMessage message){
        sessions.parallelStream().forEach(roomSession -> {
            try {
                roomSession.sendMessage(message);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        });
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // javascript에서 session.close해서 연결 끊음. 그리고 이 메소드 실행
        // session은 연결 끊긴 session을 매개변수로 이거갖고 뭐 하세요... 하고 제공해주는 것 뿐
    }
}
