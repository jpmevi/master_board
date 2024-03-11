package com.master.board.application.dao;

import com.master.board.application.dto.NewUserDto;
import com.master.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<User> findUserByEmail(String email);
    List<User> findAllUsers();

    void saveUser(NewUserDto user);
    void updateUser(User newUser);
    void deleteUser(User oldUser);
}
