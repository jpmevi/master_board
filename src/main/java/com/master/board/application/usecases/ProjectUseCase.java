package com.master.board.application.usecases;

import com.master.board.application.dao.CaseTypeDAO;
import com.master.board.application.dao.ProjectDAO;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.Project;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectUseCase {

}
