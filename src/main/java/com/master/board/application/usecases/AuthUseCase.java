package com.master.board.application.usecases;

import com.master.board.adapters.out.mysqlJDBC.repositories.UserRepository;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.LoginDto;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.services.JwtService;
import com.master.board.infraestructure.exceptions.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUseCase {

    private final UserDAO userDao;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user =userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterDto request) {
        var isPresent = userDao.findUserByEmail(request.email()).isPresent();
        if(isPresent) throw new ResourceAlreadyExistsException("user","email",request.email());
        return userDao.saveUser(request,passwordEncoder,jwtService);
    }

}
