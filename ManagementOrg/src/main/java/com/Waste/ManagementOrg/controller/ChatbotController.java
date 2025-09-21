package com.Waste.ManagementOrg.controller;


import com.Waste.ManagementOrg.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;


        @PostMapping
        public String chat(@RequestBody String userMessage) {
            return chatbotService.getResponse(userMessage);
        }
}