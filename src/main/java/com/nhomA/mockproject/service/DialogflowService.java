package com.nhomA.mockproject.service;

import java.io.IOException;

public interface DialogflowService {
    String getDialogflowResponse(String sessionId, String text) throws IOException;
}
