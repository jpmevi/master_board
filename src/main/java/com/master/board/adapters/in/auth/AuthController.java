package com.master.board.adapters.in.auth;

import com.master.board.application.dto.LoginDto;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.AuthResponse;
import com.master.board.application.usecases.AuthUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginDto request)
    {
        return ResponseEntity.ok(authUseCase.login(request));
    }
}
