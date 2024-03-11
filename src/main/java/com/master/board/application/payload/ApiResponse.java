package com.master.board.application.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ApiResponse {
    private Integer httpCode;
    private String message;
    private HttpStatus status;
    private Object data;

    public ApiResponse(Integer httpCode, String message, HttpStatus status, Object data) {
        this.httpCode = httpCode;
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
