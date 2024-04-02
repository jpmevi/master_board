package com.master.board.application.dao;

import com.master.board.adapters.out.entities.CommentEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.application.dto.CommentDto;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommentDAO {
    Optional<Comment> find(Long id);
    Optional<CommentEntity> findById(Long id);
    Page<Comment> findAllComments(Pageable pageable);

    List<Comment> findAllCaseTypesByCardItem(Long cardItemId);
    Comment saveComment(CommentDto input, UserEntity user);
    void updateComment(CommentEntity comment, CommentDto input,UserEntity user);
    void deleteComment(Long id);
}
