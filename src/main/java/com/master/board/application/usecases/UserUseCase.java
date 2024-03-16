package com.master.board.application.usecases;

import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.domain.models.User;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserUseCase {
    private final UserDAO userDao;
    public List<User> getAllUsers(){
        return userDao.findAllUsers();
    }

    public ResponseEntity<?> updateUser(Long id, RegisterDto updateUserDto) throws ResourceNotFoundException {
        try{
        var existingUser = userDao.find(id);
        if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",id);
        if(!Objects.equals(existingUser.get().getEmail(), updateUserDto.email())) {
            var isPresentEmail = userDao.findUserByEmail(updateUserDto.email()).isPresent();
            if (isPresentEmail) throw new ResourceAlreadyExistsException("user", "email", updateUserDto.email());
        }
        userDao.updateUser(existingUser.get(),updateUserDto);
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
