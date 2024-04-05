package com.master.board.application.usecases;

import com.master.board.application.dao.ProjectDAO;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.CommentDto;
import com.master.board.application.dto.ProjectDTO;
import com.master.board.application.dto.RegisterDto;
import com.master.board.domain.models.Comment;
import com.master.board.domain.models.Project;
import com.master.board.domain.models.User;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectUseCase {
    private final ProjectDAO projectDAO;
    private final UserDAO userDAO;

    public Page<Project> getAllProjects(Pageable pageable)
    {return projectDAO.findAllProjects(pageable);}

    public List<Project> getAllProjectsByProjectManager(Long userId)
    {
        return projectDAO.findAllProjectsByProjectManager(userId);
    }

    public Optional<Project> getProjectById(Long id){
        try {
            var project = projectDAO.find(id);
            if(!project.isPresent()) throw new ResourceNotFoundException("project", "id", id);
            return project;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Project saveProject(ProjectDTO request) {
        var existingUser = userDAO.find(request.user());
        if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",request.user());
        return projectDAO.saveProject(request,existingUser.get());
    }

    public Project updateProject(Long id, ProjectDTO request) throws ResourceNotFoundException {
        try{
            var existingProject = projectDAO.findById(id);
            if(!existingProject.isPresent()) throw new ResourceNotFoundException("project","id",id);
            var existingUser = userDAO.find(request.user());
            if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",request.user());
            projectDAO.updateProject(existingProject.get(),request,existingUser.get());
            return new Project(existingProject.get());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public Project deleteProject(Long id) throws ResourceNotFoundException {
        try{
            var existingProject = projectDAO.findById(id);
            if(!existingProject.isPresent()) throw new ResourceNotFoundException("project","id",id);
            projectDAO.deleteProject(id);
            return null;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<Map<String, Object>> hoursAndMoneySpecificProject(Long projectId)
    {
        return projectDAO.hoursAndMoneySpecificProject(projectId);
    }

    public List<Map<String, Object>> hoursAndMoneyPerProject()
    {
        return projectDAO.hoursAndMoneyPerProject();
    }

    public List<Map<String, Object>> projectWithMoreCanceledCases()
    {
        return projectDAO.projectWithMoreCanceledCases();
    }

    public List<Map<String, Object>> projectWithMostReportedFinishedCards()
    {
        return projectDAO.projectWithMostReportedFinishedCards();
    }

    public List<Map<String, Object>> hoursAndMoneyPerCaseType(String caseType)
    {
        return projectDAO.hoursAndMoneyPerCaseType(caseType);
    }

}
