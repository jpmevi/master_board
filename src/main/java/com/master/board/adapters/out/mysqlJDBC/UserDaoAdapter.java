package com.master.board.adapters.out.mysqlJDBC;

import com.master.board.adapters.out.mysqlJDBC.entities.UserEntity;
import com.master.board.adapters.out.mysqlJDBC.repositories.UserRepository;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.User;
import com.master.board.domain.models.enums.Role;
import com.master.board.domain.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserDaoAdapter implements UserDAO {
    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> find(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findUserByEmail(email);
        return userEntityOptional.map(userEntity ->
                new User(
                        userEntity.getId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getEmail(),
                        userEntity.getPassword(),
                        userEntity.getAddress(),
                        userEntity.getPhone(),
                        userEntity.getImgUrl(),
                        userEntity.getRole(),
                        userEntity.getAuthorities()
                )
        );
    }

    @Override
    public List<User> findAllUsers() {
        return  ((List<UserEntity>) userRepository.findAll())
                .stream()
                .map(userEntity -> {
                    return new User(
                            userEntity.getId(),
                            userEntity.getFirstName(),
                            userEntity.getLastName(),
                            userEntity.getEmail(),
                            userEntity.getPassword(),
                            userEntity.getAddress(),
                            userEntity.getPhone(),
                            userEntity.getImgUrl(),
                            userEntity.getRole(),
                            userEntity.getAuthorities()
                    );
                }).toList();
    }

    @Override
    public AuthResponse saveUser(RegisterDto request, PasswordEncoder passwordEncoder, JwtService jwtService) {
        UserEntity user = UserEntity.builder()
                .email(request.email())
                .password(passwordEncoder.encode( request.password()))
                .firstName(request.first_name())
                .lastName(request.last_name())
                .address(request.address())
                .phone(request.phone())
                .imgUrl(request.img_url())
                .role(Role.values()[request.role()])
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    @Override
    public void updateUser(UserEntity user, RegisterDto request) {

        user.setFirstName(request.first_name());
        user.setLastName(request.last_name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setAddress(request.address());
        user.setPhone(request.phone());
        user.setImgUrl(request.img_url());
        user.setRole(Role.values()[request.role()]);

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
