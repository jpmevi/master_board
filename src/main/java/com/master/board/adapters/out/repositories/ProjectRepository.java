package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {
}
