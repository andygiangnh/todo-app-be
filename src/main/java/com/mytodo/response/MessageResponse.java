package com.mytodo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {

    private LocalDateTime timestamp;

    private int status;

    private String message;

    private Map errors = new HashMap<>();

    public MessageResponse() {
        timestamp = LocalDateTime.now();
    }

    public MessageResponse(String message) {
        this();
        this.status = 200;
        this.message = message;
    }

}
