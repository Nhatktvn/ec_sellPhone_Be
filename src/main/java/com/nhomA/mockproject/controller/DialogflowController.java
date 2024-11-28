package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.service.DialogflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
//@CrossOrigin(origins = "http://localhost:3000") // Adjust the origin as needed
public class DialogflowController {

    @Autowired
    private DialogflowService dialogflowService;

    @PostMapping
    public ChatResponse getResponse(@RequestBody ChatRequest request) {
        try {
            String responseText = dialogflowService.getDialogflowResponse(request.getSessionId(), request.getMessage());
            return new ChatResponse(responseText);
        } catch (Exception e) {
            e.printStackTrace();
            return new ChatResponse("Sorry, I couldn't process your request.");
        }
    }

    // DTO Classes
    public static class ChatRequest {
        private String sessionId;
        private String message;

        // Getters and Setters

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ChatResponse {
        private String reply;

        public ChatResponse(String reply) {
            this.reply = reply;
        }

        // Getter and Setter

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }
    }
}