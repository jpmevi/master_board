package com.master.board.adapters.in.web;

import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.AuthResponse;
import com.master.board.application.usecases.UserUseCase;
import com.master.board.domain.models.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/v1")
@RequiredArgsConstructor
@EnableMethodSecurity
public class UserController {
    private final UserUseCase userUseCase;

    @PreAuthorize("hasAuthority('administrator')")
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userUseCase.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userUseCase.getUserById(userId));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody @Valid RegisterDto registerDto){
        return ResponseEntity.status(HttpStatus.OK).body(userUseCase.updateUser(userId,registerDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userUseCase.deleteUser(userId));
    }
}
