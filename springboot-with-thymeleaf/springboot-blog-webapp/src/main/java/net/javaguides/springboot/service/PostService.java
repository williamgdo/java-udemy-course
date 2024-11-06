package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
    PostDto findPostById(Long id);
    PostDto findPostByUrl(String url);
    List<PostDto> searchPosts(String query);
    List<PostDto> findPostsByUser();
    void createPost(PostDto postDto);
    void updatePost(PostDto postDto);
    void deletePost(Long id);
}
