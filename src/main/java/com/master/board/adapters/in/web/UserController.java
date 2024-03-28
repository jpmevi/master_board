package com.master.board.adapters.in.web;

import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.AuthResponse;
import com.master.board.application.usecases.UserUseCase;
import com.master.board.domain.models.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@EnableMethodSecurity
public class UserController {
    private final UserUseCase userUseCase;

    @PreAuthorize("hasAuthority('administrator')")
    @GetMapping
    @Operation(
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200", description = "Respuesta exitosa"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404", description = "Recurso no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500", description = "Internal Error",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ApiResponse<List<User>> getAllUsers(){
        return new ApiResponse(HttpStatus.OK.value(),"OK", HttpStatus.OK,userUseCase.getAllUsers());
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
