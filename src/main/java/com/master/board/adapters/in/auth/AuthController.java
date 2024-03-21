package com.master.board.adapters.in.auth;

import com.master.board.application.dto.LoginDto;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.application.usecases.AuthUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginDto request)
    {
        return ResponseEntity.ok(authUseCase.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterDto request)
    {
        return ResponseEntity.ok(authUseCase.register(request));
    }
}
