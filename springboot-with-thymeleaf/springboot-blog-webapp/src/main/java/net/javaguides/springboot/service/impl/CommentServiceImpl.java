package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.dto.CommentDto;
import net.javaguides.springboot.entity.Comment;
import net.javaguides.springboot.entity.Post;
import net.javaguides.springboot.mapper.CommentMapper;
import net.javaguides.springboot.repository.CommentRepository;
import net.javaguides.springboot.repository.PostRepository;
import net.javaguides.springboot.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            PostRepository postRepository
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<CommentDto> findAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).orElseThrow();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
