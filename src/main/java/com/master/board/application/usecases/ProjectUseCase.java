package com.master.board.application.usecases;

import com.master.board.application.dao.ProjectDAO;
import com.master.board.application.dao.UserDAO;
import com.master.board.domain.models.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
}
