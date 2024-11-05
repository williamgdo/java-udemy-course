package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.CommentDto;
import net.javaguides.springboot.dto.PostDto;
import net.javaguides.springboot.entity.Comment;
import net.javaguides.springboot.entity.Post;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PostMapper {
    // map Post entity to PostDto
    public static PostDto mapToPostDto(Post post) {
        Set<CommentDto> comments = null;

        if (post.getComments() != null)
            comments = post.getComments().stream()
                    .map(CommentMapper::mapToCommentDto)
                    .collect(Collectors.toSet());

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .comments(comments)
                .build();
    }

    // map PostDto to Post Entity
    public static Post mapToPost(PostDto postDto) {
        Set<Comment> comments = null;

        if (postDto.getComments() != null)
            comments = postDto.getComments().stream()
                    .map(CommentMapper::mapToComment)
                    .collect(Collectors.toSet());

        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .shortDescription(postDto.getShortDescription())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .comments(comments)
                .build();
    }
}
