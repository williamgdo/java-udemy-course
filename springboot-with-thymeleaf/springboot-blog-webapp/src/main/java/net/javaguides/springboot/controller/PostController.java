package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.dto.PostDto;
import net.javaguides.springboot.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create handler method, GET request and return model and view
    @GetMapping("/admin/posts")
    public String getPosts(Model model) {
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }

    @GetMapping("/admin/posts/search")
    public String getPosts(
            @RequestParam(value = "query") String query,
            Model model
    ) {
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }

    @GetMapping("/admin/posts/new-post")
    public String newPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "/admin/new-post";
    }

    @PostMapping("/admin/posts")
    public String createPost(
        @Valid @ModelAttribute("post") PostDto postDto,
        BindingResult result,
        Model model
    ) {
        if(result.hasErrors()) {
            model.addAttribute("post", postDto);
            return "/admin/new-post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(
            @PathVariable("postId") Long postId,
            Model model
    ) {
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit-post";
    }

    @PostMapping("/admin/posts/{postId}/edit")
    public String updatePost(
            @PathVariable("postId") Long postId,
            @Valid @ModelAttribute("post") PostDto postDto,
            BindingResult result,
            Model model
    ) {
        if(result.hasErrors()) {
            model.addAttribute("post", postDto);
            return "/admin/new-post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/delete")
    public String updatePost(
            @PathVariable("postId") Long postId
    ) {
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postUrl}/view")
    public String getPost(
            @PathVariable("postUrl") String postUrl,
            Model model
    ) {
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view-post";
    }

    private static String getUrl(String postTitle) {
        return postTitle.trim()
                .toLowerCase()
                .replaceAll("\\s+", "-")
                .replaceAll("[^A-Za-z0-9]", "-");
    }

}
