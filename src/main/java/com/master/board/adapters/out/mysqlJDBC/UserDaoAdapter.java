package com.master.board.adapters.out.mysqlJDBC;

import com.master.board.adapters.out.mysqlJDBC.entities.UserEntity;
import com.master.board.adapters.out.mysqlJDBC.repositories.UserRepository;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.NewUserDto;
import com.master.board.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserDaoAdapter implements UserDAO {
    private final UserRepository userRepository;
    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return  ((List<UserEntity>) userRepository.findAll())
                .stream()
                .map(userEntity -> {
                    return new User(
                            userEntity.id(),
                            userEntity.firstName(),
                            userEntity.lastName(),
                            userEntity.email(),
                            userEntity.password(),
                            userEntity.address(),
                            userEntity.phone(),
                            userEntity.imgUrl(),
                            userEntity.role()
                    );
                }).toList();
    }

    @Override
    public void saveUser(NewUserDto user) {
        userRepository.save(new UserEntity(
                null,
                user.first_name(),
                user.last_name(),
                user.email(),
                user.password(),
                user.address(),
                user.phone(),
                user.img_url(),
                user.role(),
                null
        ));
    }

    @Override
    public void updateUser(User newUser) {
        userRepository.save(new UserEntity(
                newUser.id(),
                newUser.firstName(),
                newUser.lastName(),
                newUser.email(),
                newUser.password(),
                newUser.address(),
                newUser.phone(),
                newUser.imgUrl(),
                newUser.role(),
                null
        ));
    }

    @Override
    public void deleteUser(User oldUser) {

    }
}
