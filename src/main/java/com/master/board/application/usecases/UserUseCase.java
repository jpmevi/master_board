package com.master.board.application.usecases;

import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.User;
import com.master.board.domain.models.enums.Role;
import com.master.board.domain.services.JwtAuthenticationFilter;
import com.master.board.domain.services.JwtService;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserUseCase {
    private final UserDAO userDao;
    private final PasswordEncoder passwordEncoder;
    public Page<User> getAllUsers(Pageable pageable){
        return userDao.findAllUsers(pageable);
    }

    public ResponseEntity<?> getUserById(Long id){
        try{
            var user = userDao.find(id);
            if(!user.isPresent()) throw new ResourceNotFoundException("user","id",id);
            return new ResponseEntity<>( user,HttpStatus.OK);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public Optional<User> getUserInfo(Authentication authentication){
        try{
            var user = userDao.findUserByEmail(authentication.getName());
            return user;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<User> getUserByRoleAndName(String roleName, String userName) throws ResourceNotFoundException{
        try{
            if (!Role.contains(roleName)) {
                throw new ResourceNotFoundException("Role","name",roleName);
            }
            return userDao.getUserByRoleAndName(roleName,userName);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<User> getUsersByRole(String roleName) throws ResourceNotFoundException{
        try{
            if (!Role.contains(roleName)) {
                throw new ResourceNotFoundException("Role","name",roleName);
            }
            return userDao.getUsersByRole(roleName);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public User saveUser(RegisterDto request) {
        var isPresent = userDao.findUserByEmail(request.email()).isPresent();
        if(isPresent) throw new ResourceAlreadyExistsException("user","email",request.email());
        return userDao.saveUser(request,passwordEncoder);
    }

    public ResponseEntity<?> updateUser(Long id, RegisterDto updateUserDto) throws ResourceNotFoundException {
        try{
        var existingUser = userDao.find(id);
        if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",id);
        if(!Objects.equals(existingUser.get().getEmail(), updateUserDto.email())) {
            var isPresentEmail = userDao.findUserByEmail(updateUserDto.email()).isPresent();
            if (isPresentEmail) throw new ResourceAlreadyExistsException("user", "email", updateUserDto.email());
        }
        userDao.updateUser(existingUser.get(),updateUserDto, passwordEncoder);
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteUser(Long id) throws ResourceNotFoundException {
        try{
            var existingUser = userDao.find(id);
            if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",id);
            userDao.deleteUser(id);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
