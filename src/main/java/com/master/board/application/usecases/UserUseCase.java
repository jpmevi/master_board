package com.master.board.application.usecases;

import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.NewUserDto;
import com.master.board.domain.User;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserUseCase {
    private final UserDAO userDao;
    public ResponseEntity<?> saveUser(NewUserDto newUserDto) throws ResourceAlreadyExistsException {
        try{
            var isPresent = userDao.findUserByEmail(newUserDto.email()).isPresent();
            if(isPresent) throw new ResourceAlreadyExistsException("user","email",newUserDto.email());

            userDao.saveUser(newUserDto);
            return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }

    }

    public List<User> getAllUsers(){
        return userDao.findAllUsers();
    }

    public String updateUser(User user) throws ResourceNotFoundException {
        var isPresent = userDao.findUserByEmail(user.email()).isPresent();
        if(!isPresent) throw new ResourceNotFoundException("user","email",user.email());
        userDao.updateUser(user);
        return "User Succesfully Updated";
    }
}
