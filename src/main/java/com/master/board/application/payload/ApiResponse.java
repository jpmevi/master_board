package com.master.board.application.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
public class ApiResponse<T> {
    private Integer httpCode;
    private String message;
    private HttpStatus status;
    private T data;

    public ApiResponse(Integer httpCode, String message, HttpStatus status, T data) {
        this.httpCode = httpCode;
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
