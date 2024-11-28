package com.nhomA.mockproject.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.Struct;
import com.nhomA.mockproject.service.DialogflowService;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class DialogflowServiceImpl implements DialogflowService {
    @Value("${dialogflow.project-id}")
    private String projectId;

    @Value("${dialogflow.credentials-path}")
    private String credentialsPath;

    @Override
    public String getDialogflowResponse(String sessionId, String text) throws IOException {
        SessionName sessionName = SessionName.of(projectId, sessionId);
        SessionsClient sessionsClient;
        try (SessionsClient sessionsClientTemp = SessionsClient.create(
                SessionsSettings.newBuilder()
                        .setCredentialsProvider(() -> GoogleCredentials.fromStream(new FileInputStream(credentialsPath)))
                        .build())) {
            sessionsClient = sessionsClientTemp;

            // Set the text (query) and language code
            TextInput.Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode("en-US");

            // Build the query input
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            // Detect intent
            DetectIntentResponse response = sessionsClient.detectIntent(sessionName, queryInput);
            QueryResult queryResult = response.getQueryResult();

            return queryResult.getFulfillmentText();
        }
    }
}
