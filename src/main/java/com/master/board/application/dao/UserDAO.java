package com.master.board.application.dao;

import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.User;
import com.master.board.domain.services.JwtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<UserEntity> find(Long id);
    Optional<User> findUserByEmail(String email);
    List<User> getUserByRoleAndName(String roleName, String userName);
    Page<User> findAllUsers(Pageable pageable);

    User saveUser(RegisterDto user,PasswordEncoder passwordEncoder);
    void updateUser(UserEntity user,RegisterDto input);
    void deleteUser(Long id);
}
