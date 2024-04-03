package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.CardEntity;
import com.master.board.adapters.out.entities.CardItemEntity;
import com.master.board.adapters.out.entities.CardUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardUserRepository extends JpaRepository<CardUserEntity,Long> {

    @Query(
            value = "SELECT * FROM card_user c WHERE c.card_id = :cardId",
            nativeQuery = true)
    List<CardUserEntity> findAllByCard(@Param("cardId") Long cardId);
}
