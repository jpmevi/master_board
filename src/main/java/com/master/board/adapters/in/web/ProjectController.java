package com.master.board.adapters.in.web;

import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.dto.ProjectDTO;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.PaginatedResponse;
import com.master.board.application.usecases.CaseTypeUseCase;
import com.master.board.application.usecases.ProjectUseCase;
import com.master.board.application.usecases.UserUseCase;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.User;
import com.master.board.domain.models.Project;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/project")
@RequiredArgsConstructor
@EnableMethodSecurity
public class ProjectController {

    private final ProjectUseCase projectUseCase;

    @PreAuthorize("hasAuthority('administrator')")
    @GetMapping
    @Operation(
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200", description = "Respuesta exitosa",
                            content = @Content(mediaType = "application/json")
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
    public PaginatedResponse<List<Project>> getAllProjects(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<Project> projectPage = projectUseCase.getAllProjects(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"OK", HttpStatus.OK,projectPage.getContent(),projectPage.getPageable());
    }

    @GetMapping("/{projectId}")
    @Operation(
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200", description = "Respuesta exitosa",
                            content = @Content(mediaType = "application/json")
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
    public ApiResponse<Project> getProjectById(@PathVariable Long projectId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,projectUseCase.getProjectById(projectId));
    }

    @GetMapping("project-manager/{userId}")
    @Operation(
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200", description = "Respuesta exitosa",
                            content = @Content(mediaType = "application/json")
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
    public ApiResponse<List<Project>> getAllCaseTypesByProject(@PathVariable Long userId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,projectUseCase.getAllProjectsByProjectManager(userId));
    }

    @PreAuthorize("hasAuthority('administrator')")
    @Operation(
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200", description = "Respuesta exitosa",
                            content = @Content(mediaType = "application/json")
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
    @PostMapping()
    public ApiResponse<Project> saveProject(@RequestBody @Valid ProjectDTO request)
    {
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,projectUseCase.saveProject(request));
    }

    @PreAuthorize("hasAuthority('administrator')")
    @Operation(
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200", description = "Respuesta exitosa",
                            content = @Content(mediaType = "application/json")
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

    @PutMapping("/{projectId}")
    public ApiResponse<Project> updateProject(@PathVariable Long projectId, @RequestBody @Valid ProjectDTO request)
    {
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,projectUseCase.updateProject(projectId, request));
    }

    @PreAuthorize("hasAuthority('administrator')")
    @Operation(
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200", description = "Respuesta exitosa",
                            content = @Content(mediaType = "application/json")
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

    @DeleteMapping("/{projectId}")
    public ApiResponse<Project> deleteProject(@PathVariable Long projectId)
    {
        return new ApiResponse(HttpStatus.NO_CONTENT.value(),"Success", HttpStatus.NO_CONTENT,projectUseCase.deleteProject(projectId));
    }
}
