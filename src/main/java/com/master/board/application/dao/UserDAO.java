package com.master.board.application.dao;

import com.master.board.adapters.out.mysqlJDBC.entities.UserEntity;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.User;
import com.master.board.domain.services.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<UserEntity> find(Long id);
    Optional<UserEntity> findUserByEmail(String email);
    List<User> findAllUsers();

    AuthResponse saveUser(RegisterDto user,PasswordEncoder passwordEncoder, JwtService jwtService);
    void updateUser(UserEntity user,RegisterDto input);
    void deleteUser(Long id);
}
