package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.adapters.out.repositories.UserRepository;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.User;
import com.master.board.domain.models.enums.Role;
import com.master.board.domain.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                        userEntity.getAddress(),
                        userEntity.getPhone(),
                        userEntity.getImgUrl(),
                        userEntity.getRole(),
                        userEntity.getAuthorities()
                )
        );
    }

    @Override
    public List<User> getUserByRoleAndName(String roleName,String userName) {
        return  ((List<UserEntity>) userRepository.getUserByRoleAndName(roleName,userName))
                .stream()
                .map(userEntity -> {
                    return new User(
                            userEntity.getId(),
                            userEntity.getFirstName(),
                            userEntity.getLastName(),
                            userEntity.getEmail(),
                            userEntity.getAddress(),
                            userEntity.getPhone(),
                            userEntity.getImgUrl(),
                            userEntity.getRole(),
                            userEntity.getAuthorities()
                    );
                }).toList();

    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        Page<UserEntity> userEntitiesPage = userRepository.findAll(pageable);

        List<User> users = userEntitiesPage.getContent().stream()
                .map(userEntity -> new User(
                        userEntity.getId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getEmail(),
                        userEntity.getAddress(),
                        userEntity.getPhone(),
                        userEntity.getImgUrl(),
                        userEntity.getRole(),
                        userEntity.getAuthorities()
                ))
                .toList();

        return new PageImpl<>(users, pageable, userEntitiesPage.getTotalElements());
    }


    @Override
    public User saveUser(RegisterDto request, PasswordEncoder passwordEncoder) {
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
        return new User(user);
    }

    @Override
    public void updateUser(UserEntity user, RegisterDto request, PasswordEncoder passwordEncoder) {

        user.setFirstName(request.first_name());
        user.setLastName(request.last_name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode( request.password()));
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
