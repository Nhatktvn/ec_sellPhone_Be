//package com.nhomA.mockproject.controller;
//
//import com.google.cloud.dialogflow.v2.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/chat")
//public class ChatController {
//    @PostMapping
//    public ResponseEntity<?> sendMessageToDialogflow(@RequestParam("message") String message) throws IOException {
//        String projectId = "test-oown";  // Thay đổi thông tin project của bạn
//
//        String sessionId = UUID.randomUUID().toString();
//        SessionsClient sessionsClient = SessionsClient.create();
//        SessionName session = SessionName.of(projectId, sessionId);
//
//        // Xây dựng yêu cầu gửi đến Dialogflow
//        TextInput.Builder textInput = TextInput.newBuilder().setText(message).setLanguageCode("en-US");
//        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
//
//        // Gửi yêu cầu và nhận phản hồi từ Dialogflow
//        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
//        QueryResult result = response.getQueryResult();
//
//        sessionsClient.close();
//
//        // Xử lý phản hồi từ Dialogflow
//        if (result.getIntent().getDisplayName().equals("Navigate")) {
//            return ResponseEntity.ok(result.getFulfillmentText());
//        } else {
//            return ResponseEntity.ok("Command not recognized");
//        }
//    }
//}
