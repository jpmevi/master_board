package com.master.board.application.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
public class PaginatedResponse<T> {
    private Integer httpCode;
    private String message;
    private HttpStatus status;
    private T data;
    private Pageable pageable;

    public PaginatedResponse(Integer httpCode, String message, HttpStatus status, T data, Pageable pageable) {
        this.httpCode = httpCode;
        this.message = message;
        this.status = status;
        this.data = data;
        this.pageable = pageable;
    }
}
