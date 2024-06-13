package com.project.pc.service.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pc.config.websocket.ChatRoom;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ObjectMapper mapper;

    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name){
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public void removeRoom(String roomId){
        chatRooms.remove(roomId);
    }
}
