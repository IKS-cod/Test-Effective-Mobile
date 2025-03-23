package com.effective_mobile.controller;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CommentFromDbDto;
import com.effective_mobile.dto.CreateOrUpdateCommentsDto;
import com.effective_mobile.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@Tag(name = "Комментарии")
public class CommentsController {

    private static final Logger logger = LoggerFactory.getLogger(CommentsController.class);
    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("@userVerification.verificationUserForComment(#id) || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{id}")
    @Operation(summary = "Создание комментария")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public CommentFromDbDto createComment(
            @PathVariable Long id,
            @RequestBody CreateOrUpdateCommentsDto createOrUpdateCommentsDto) {
        return commentService.createComment(id, createOrUpdateCommentsDto);
    }
}
