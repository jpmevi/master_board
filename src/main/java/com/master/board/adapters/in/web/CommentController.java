package com.master.board.adapters.in.web;

import com.master.board.application.dto.CaseTypeFlowDto;
import com.master.board.application.dto.CommentDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.PaginatedResponse;
import com.master.board.application.usecases.CommentUseCase;
import com.master.board.domain.models.CaseTypeFlow;
import com.master.board.domain.models.Comment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
@EnableMethodSecurity
@CrossOrigin("http://localhost:4200")
public class CommentController {
    private final CommentUseCase commentUseCase;

    @GetMapping
    public PaginatedResponse<List<Comment>> getAllComments(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<Comment> commentPage = commentUseCase.getAllComments(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"OK", HttpStatus.OK,commentPage.getContent(),commentPage.getPageable());
    }

    @GetMapping("/{commentId}")
    public ApiResponse<Comment> getCommentById(@PathVariable Long commentId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,commentUseCase.getCommentById(commentId));
    }

    @GetMapping("getByCardItem/{cardItemId}")
    public ApiResponse<Comment> getCommentByCardItem(@PathVariable Long cardItemId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,commentUseCase.getAllCommentsByCardItem(cardItemId));
    }

    @PostMapping
    public ApiResponse<Comment> saveComment(@RequestBody @Valid CommentDto request)
    {
        return new ApiResponse(HttpStatus.CREATED.value(),"Success", HttpStatus.CREATED,commentUseCase.saveComment(request));
    }

    @PutMapping("/{commentId}")
    public ApiResponse<Comment> updateComment(@PathVariable Long commentId, @RequestBody @Valid CommentDto commentDto){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,commentUseCase.updateComment(commentId,commentDto));
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<Null> deleteComment(@PathVariable Long commentId){
        return new ApiResponse(HttpStatus.NO_CONTENT.value(),"Success", HttpStatus.NO_CONTENT,commentUseCase.deleteComment(commentId));
    }
}
