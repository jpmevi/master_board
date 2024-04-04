package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {

    @Query(
            value = "SELECT * FROM project p WHERE p.user_id = :userId",
            nativeQuery = true)
    List<ProjectEntity> findAllByProjectManager(@Param("userId") Long userId);

    @Query(
            value = "SELECT " +
                    "p.name AS project_name, " +
                    "SUM(ci.hours) AS total_hours, " +
                    "SUM(ci.hours * u.salary_per_hour) AS total_money_invested " +
                    "FROM masterboard.project p " +
                    "LEFT JOIN masterboard.card c ON p.id = c.project_id " +
                    "LEFT JOIN masterboard.card_item ci ON c.id = ci.card_id " +
                    "LEFT JOIN masterboard.card_user cu ON c.id = cu.card_id " +
                    "LEFT JOIN masterboard.user u ON cu.user_id = u.id " +
                    "WHERE p.id = :projectId " +
                    "GROUP BY p.id",
            nativeQuery = true
    )
    List<Object[]> hoursAndMoneySpecificProject(@Param("projectId") Long projectId);

    @Query(
            value = "SELECT " +
                    "p.name AS project_name, " +
                    "SUM(ci.hours) AS total_hours, " +
                    "SUM(ci.hours * u.salary_per_hour) AS total_money_invested " +
                    "FROM masterboard.project p " +
                    "LEFT JOIN masterboard.card c ON p.id = c.project_id " +
                    "LEFT JOIN masterboard.card_item ci ON c.id = ci.card_id " +
                    "LEFT JOIN masterboard.card_user cu ON c.id = cu.card_id " +
                    "LEFT JOIN masterboard.user u ON cu.user_id = u.id " +
                    "GROUP BY p.id",
            nativeQuery = true
    )
    List<Object[]> hoursAndMoneyPerProject();

    @Query(
            value = "SELECT p.*, COUNT(c.id) AS num_canceled_cases " +
                    "FROM masterboard.project p " +
                    "LEFT JOIN masterboard.card c ON p.id = c.project_id AND c.state = 'canceled' " +
                    "GROUP BY p.id " +
                    "ORDER BY num_canceled_cases DESC " +
                    "LIMIT 1",
            nativeQuery = true
    )
    List<Object[]> projectWithMoreCanceledCases();


}
