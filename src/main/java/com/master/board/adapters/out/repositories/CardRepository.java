package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.CardEntity;
import com.master.board.adapters.out.entities.CaseTypeFlowEntity;
import com.master.board.adapters.out.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity,Long> {

    @Query(
            value = "SELECT c.* FROM card c JOIN card_user cu ON c.id = cu.card_id WHERE cu.user_id = :userId",
            nativeQuery = true
    )
    List<CardEntity> findAllByUserId(@Param("userId") Long userId);


}
