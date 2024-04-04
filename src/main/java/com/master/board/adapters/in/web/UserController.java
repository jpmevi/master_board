package com.master.board.adapters.in.web;

import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.AuthResponse;
import com.master.board.application.payload.PaginatedResponse;
import com.master.board.application.usecases.UserUseCase;
import com.master.board.domain.models.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@EnableMethodSecurity
public class UserController {
    private final UserUseCase userUseCase;

    @PreAuthorize("hasAuthority('administrator')")
    @GetMapping
    @Operation(
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200", description = "Respuesta exitosa"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404", description = "Recurso no encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500", description = "Internal Error",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public PaginatedResponse<List<User>> getAllUsers(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<User> usersPage = userUseCase.getAllUsers(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"OK", HttpStatus.OK,usersPage.getContent(),usersPage.getPageable());
    }

    @GetMapping("/role/{roleName}")
    public ApiResponse<List<User>> getUsersByRole(@PathVariable String roleName){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,userUseCase.getUsersByRole(roleName));
    }

    @GetMapping("/mostPaidDeveloper")
    public ApiResponse<List<Map<String, Object>>> getMostPaidDeveloper(){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,userUseCase.mostPaidDeveloper());
    }

    @GetMapping("/developerWithMoreCases")
    public ApiResponse<List<Map<String, Object>>> developerWithMostAssignedCards(){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,userUseCase.developerWithMostAssignedCards());
    }


    @GetMapping("/developersReport")
    public ApiResponse<List<Object[]>> developersReport(){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,userUseCase.developersReport());
    }

    @GetMapping("/hoursAndMoneyByDeveloper/{userId}")
    public ApiResponse<List<Object[]>> hoursAndMoneyByDeveloper(@PathVariable Long userId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,userUseCase.hoursAndMoneyByDeveloper(userId));
    }
    @GetMapping("/{roleName}/{userName}")
    public ApiResponse<List<User>> getUserByRoleAndName(@PathVariable String roleName,@PathVariable String userName){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,userUseCase.getUserByRoleAndName(roleName,userName));
    }
    @GetMapping("/userInfo")
    public ApiResponse<User> getUserInfo(Authentication authentication){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,userUseCase.getUserInfo(authentication));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userUseCase.getUserById(userId));
    }

    @PreAuthorize("hasAuthority('administrator')")
    @PostMapping()
    public ApiResponse<User> saveUser(@RequestBody @Valid RegisterDto request)
    {
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,userUseCase.saveUser(request));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody @Valid RegisterDto registerDto){
        return ResponseEntity.status(HttpStatus.OK).body(userUseCase.updateUser(userId,registerDto));
    }

    @PreAuthorize("hasAuthority('administrator')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userUseCase.deleteUser(userId));
    }
}
