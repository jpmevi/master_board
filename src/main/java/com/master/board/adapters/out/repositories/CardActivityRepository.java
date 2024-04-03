package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.CardActivityEntity;
import com.master.board.adapters.out.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardActivityRepository extends JpaRepository<CardActivityEntity,Long> {

    @Query(
            value = "SELECT * FROM card_activity c WHERE c.card_id = :cardId",
            nativeQuery = true)
    List<CardActivityEntity> findAllByCard(@Param("cardId") Long cardId);

}
