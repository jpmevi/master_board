package com.master.board.application.usecases;

import com.master.board.application.dao.CommentDAO;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.CommentDto;
import com.master.board.application.dto.CommentDto;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.Comment;
import com.master.board.domain.models.Comment;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentUseCase {
    private final CommentDAO commentDAO;
    private final UserDAO userDAO;
    public Page<Comment> getAllComments(Pageable pageable){
        return commentDAO.findAllComments(pageable);
    }

    public List<Comment> getAllCommentsByCardItem(Long cardItemId){
        return commentDAO.findAllCaseTypesByCardItem(cardItemId);
    }

    public Optional<Comment> getCommentById(Long id){
        try{
            var comment = commentDAO.find(id);
            if(!comment.isPresent()) throw new ResourceNotFoundException("comment","id",id);
            return comment;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public Comment saveComment(CommentDto request) {
        var existingUser = userDAO.find(request.userId());
        if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",request.userId());
        return commentDAO.saveComment(request,existingUser.get());
    }

    public Comment updateComment(Long id, CommentDto commentDto) throws ResourceNotFoundException {
        try{
            var existingComment = commentDAO.findById(id);
            if(!existingComment.isPresent()) throw new ResourceNotFoundException("comment","id",id);
            var existingUser = userDAO.find(commentDto.userId());
            if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",commentDto.userId());
            commentDAO.updateComment(existingComment.get(),commentDto,existingUser.get());
            return new Comment(existingComment.get());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteComment(Long id) throws ResourceNotFoundException {
        try{
            var existingComment = commentDAO.findById(id);
            if(!existingComment.isPresent()) throw new ResourceNotFoundException("comment","id",id);
            commentDAO.deleteComment(id);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
