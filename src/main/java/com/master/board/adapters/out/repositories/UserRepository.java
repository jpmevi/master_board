package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query(
            value = "SELECT * FROM user u WHERE u.email = :email",
            nativeQuery = true)
    Optional<UserEntity> findUserByEmail(@Param("email") String email);

    @Query(
            value = "SELECT * FROM user u WHERE u.role = :roleName AND u.first_name LIKE %:userName%",
            nativeQuery = true)
    List<UserEntity> getUserByRoleAndName(@Param("roleName") String roleName, @Param("userName") String userName);

    @Query(
            value = "SELECT * FROM user u WHERE u.role = :roleName",
            nativeQuery = true)
    List<UserEntity> getUsersByRole(@Param("roleName") String roleName);

    @Query(
            value = "SELECT u.*, SUM(ci.hours * u.salary_per_hour) AS total_payment " +
                    "FROM masterboard.card_item ci " +
                    "JOIN masterboard.card c ON ci.card_id = c.id " +
                    "JOIN masterboard.card_user cu ON c.id = cu.card_id " +
                    "JOIN masterboard.user u ON cu.user_id = u.id " +
                    "GROUP BY u.id " +
                    "ORDER BY total_payment DESC " +
                    "LIMIT 1",
            nativeQuery = true
    )
    List<Map<String, Object>> mostPaidDeveloper();

    @Query(
            value = "SELECT u.*, COUNT(cu.id) AS num_assigned_cards " +
                    "FROM masterboard.card_user cu " +
                    "JOIN masterboard.user u ON cu.user_id = u.id " +
                    "GROUP BY u.id " +
                    "ORDER BY num_assigned_cards DESC " +
                    "LIMIT 1",
            nativeQuery = true
    )
    List<Map<String, Object>> developerWithMostAssignedCards();

    @Query(
            value = "SELECT u.*, " +
                    "SUM(ci.hours * u.salary_per_hour) AS total_earned, " +
                    "COUNT(DISTINCT cu.card_id) AS num_completed_cards " +
                    "FROM masterboard.user u " +
                    "LEFT JOIN masterboard.card_user cu ON u.id = cu.user_id " +
                    "LEFT JOIN masterboard.card_item ci ON cu.card_item_id = ci.id "+
                    "WHERE u.role = 'developer'" +
                    "GROUP BY u.id",
            nativeQuery = true
    )
    List<Map<String, Object>> developersReport();

    @Query(
            value = "SELECT u.id AS user_id, u.*, " +
                    "SUM(ci.hours) AS total_hours, " +
                    "SUM(ci.hours * u.salary_per_hour) AS total_money_invested " +
                    "FROM masterboard.user u " +
                    "LEFT JOIN masterboard.card_user cu ON u.id = cu.user_id " +
                    "LEFT JOIN masterboard.card_item ci ON cu.card_item_id = ci.id " +
                    "WHERE u.id = :userId " +
                    "GROUP BY u.id",
            nativeQuery = true
    )
    List<Map<String, Object>> hoursAndMoneyByDeveloper(@Param("userId") Long userId);



}
