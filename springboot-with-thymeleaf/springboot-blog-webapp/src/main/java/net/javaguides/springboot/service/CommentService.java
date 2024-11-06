package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findAllComments();
    void createComment(String postUrl, CommentDto commentDto);
    void deleteComment(Long id);
    List<CommentDto> findCommentByPost();
}
