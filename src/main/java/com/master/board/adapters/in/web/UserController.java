package com.master.board.adapters.in.web;

import com.master.board.application.dto.NewUserDto;
import com.master.board.application.usecases.UserUseCase;
import com.master.board.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase userUseCase;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userUseCase.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid NewUserDto newUserDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userUseCase.saveUser(newUserDto));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userUseCase.updateUser(user));
    }
}
