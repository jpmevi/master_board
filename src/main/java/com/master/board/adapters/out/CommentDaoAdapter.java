package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.CommentEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.adapters.out.repositories.CommentRepository;
import com.master.board.application.dao.CommentDAO;
import com.master.board.application.dto.CommentDto;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.Comment;
import com.master.board.domain.models.Project;
import com.master.board.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CommentDaoAdapter implements CommentDAO {
    private final CommentRepository commentRepository;

    @Override
    public Optional<Comment> find(Long id) {
        return commentRepository.findById(id)
                .map(commentEntity -> {
                    User user = new User(commentEntity.getUser());
                    return new Comment(
                            commentEntity.getId(),
                            commentEntity.getComment(),
                            user,
                            commentEntity.getCardItemId(),
                            commentEntity.getCreatedAt(),
                            commentEntity.getUpdatedAt()
                    );
                });
    }

    @Override
    public Optional<CommentEntity> findById(Long id) {
        return commentRepository.findById(id);
    }



    @Override
    public Page<Comment> findAllComments(Pageable pageable) {
        Page<CommentEntity> commentEntitiesPage = commentRepository.findAll(pageable);
        List<Comment> comments = commentEntitiesPage.getContent().stream()
                .map(commentEntity -> {
                    User user = new User(commentEntity.getUser());
                    return new Comment(
                            commentEntity.getId(),
                            commentEntity.getComment(),
                            user,
                            commentEntity.getCardItemId(),
                            commentEntity.getCreatedAt(),
                            commentEntity.getUpdatedAt()
                    );
                })
                .toList();
        return new PageImpl<>(comments, pageable, commentEntitiesPage.getTotalElements());
    }

    @Override
    public List<Comment> findAllCaseTypesByCardItem(Long cardItemId) {
        return  ((List<CommentEntity>) commentRepository.findAllByCardItem(cardItemId))
                .stream()
                .map(commentEntity -> {
                    User user = new User(commentEntity.getUser());
                    return new Comment(
                            commentEntity.getId(),
                            commentEntity.getComment(),
                            user,
                            commentEntity.getCardItemId(),
                            commentEntity.getCreatedAt(),
                            commentEntity.getUpdatedAt()
                    );
                }).toList();
    }
    @Override
    public Comment saveComment(CommentDto request, UserEntity user) {
        CommentEntity comment = CommentEntity.builder()
                .comment(request.comment())
                .user(user)
                .cardItemId(request.cardItemId())
                .build();
        commentRepository.save(comment);

        return new Comment(comment);
    }

    @Override
    public void updateComment(CommentEntity comment, CommentDto input, UserEntity user) {

        comment.setComment(input.comment());
        comment.setUser(user);
        comment.setCardItemId(input.cardItemId());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
