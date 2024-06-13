package com.project.pc.controller;

import com.project.pc.config.websocket.ChatRoom;
import com.project.pc.service.chat.ChatService;
import com.project.pc.service.kakaoTalk.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // 채팅방 리스트는 굳이 없어도 되기 때문에 주석

//    @RequestMapping("/chat/chatList")
//    public String chatList(Model model){
//        List<ChatRoom> roomList = chatService.findAllRoom();
//        model.addAttribute("roomList", roomList);
//        return "chat/chatList";
//    }

    @PostMapping("/chat/createRoom")
    @ResponseBody
    public ChatRoom createRoom(Model model, @RequestParam("name") String name, String username){
        System.out.println("채팅을 만들려는 사람 : " + name);
        ChatRoom room = chatService.createRoom(name);
        model.addAttribute("room", room);
        model.addAttribute("username", username);
        return room;
    }

    @GetMapping("/chat/chatRoom/{roomId}")
    @ResponseBody
    public ChatRoom chatRoom(@PathVariable("roomId") String roomId){
        return chatService.findRoomById(roomId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/qna")
    public String qnaList(Model model){
        System.out.println(chatService.findAllRoom());
        model.addAttribute("roomList", chatService.findAllRoom());
        return "admin/adminQnA";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/chatRoom")
    public String adminChatRoom(@RequestParam("roomId") String roomId, Model model){
        System.out.println(roomId);
        model.addAttribute("chatRoom", chatService.findRoomById(roomId));
        return "admin/chatRoom";
    }
}