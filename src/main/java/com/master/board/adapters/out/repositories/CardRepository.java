package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.CardEntity;
import com.master.board.adapters.out.entities.CaseTypeFlowEntity;
import com.master.board.adapters.out.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity,Long> {

    @Query(
            value = "SELECT * FROM card c WHERE c.case_type_id = :caseTypeId",
            nativeQuery = true)
    List<CardEntity> findAllByCaseType(@Param("caseTypeId") Long caseTypeId);

    @Query(
            value = "SELECT * FROM card c WHERE c.project_id = :projectId",
            nativeQuery = true)
    List<CardEntity> findAllByProject(@Param("projectId") Long projectId);

    @Query(
            value = "SELECT p.name AS project_name, COUNT(c.id) AS num_cards " +
                    "FROM masterboard.project p " +
                    "LEFT JOIN masterboard.card c ON p.id = c.project_id " +
                    "GROUP BY p.id",
            nativeQuery = true
    )
    List<Object[]> numberOfCardsReport();

    @Query(
            value = "SELECT DATE(ci.created_at) AS date, " +
                    "SUM(ci.hours) AS total_hours, " +
                    "SUM(ci.hours * u.salary_per_hour) AS total_money_invested " +
                    "FROM masterboard.card_item ci " +
                    "LEFT JOIN masterboard.card_user cu ON ci.card_id = cu.card_id " +
                    "LEFT JOIN masterboard.user u ON cu.user_id = u.id " +
                    "WHERE ci.created_at BETWEEN :startDate AND :endDate " +
                    "GROUP BY DATE(ci.created_at)",
            nativeQuery = true
    )
    List<Object[]> hoursAndMoneyByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
