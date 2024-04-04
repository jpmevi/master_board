package com.master.board.application.dao;

import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.User;
import com.master.board.domain.services.JwtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDAO {

    Optional<UserEntity> find(Long id);
    Optional<User> findUserByEmail(String email);
    List<User> getUserByRoleAndName(String roleName, String userName);
    List<User> getUsersByRole(String roleName);
    Page<User> findAllUsers(Pageable pageable);
    User saveUser(RegisterDto user,PasswordEncoder passwordEncoder);
    void updateUser(UserEntity user,RegisterDto input, PasswordEncoder passwordEncoder);
    void deleteUser(Long id);
    List<Map<String, Object>> mostPaidDeveloper();
    List<Map<String, Object>> developerWithMostAssignedCards();

    List<Map<String, Object>> developersReport();

    List<Map<String, Object>> hoursAndMoneyByDeveloper(Long userId);
}
