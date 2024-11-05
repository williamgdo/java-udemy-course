package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.dto.CommentDto;
import net.javaguides.springboot.dto.PostDto;
import net.javaguides.springboot.service.CommentService;
import net.javaguides.springboot.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;


    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/{postUrl}/comments")
    public String createComment (
        @PathVariable("postUrl") String postUrl,
        @Valid @ModelAttribute("comment") CommentDto comment,
        BindingResult result,
        Model model
    ) {
        PostDto postDto = postService.findPostByUrl(postUrl);

        if(result.hasErrors()) {
            model.addAttribute("post", postDto);
            model.addAttribute("comment", comment);
            return "blog/view";
        }
        commentService.createComment(postUrl, comment);
        return "redirect:/post/" + postUrl;
    }

    @GetMapping("/admin/comments")
    public String getComments(
            Model model
    ) {
        List<CommentDto> comments = commentService.findAllComments();
        model.addAttribute("comments", comments);
        return "admin/comments";
    }

    @GetMapping("/admin/comments/{commentId}/delete")
    public String deleteComment(
            @PathVariable("commentId") Long commentId
    ) {
        commentService.deleteComment(commentId);
        return "redirect:/admin/comments";
    }
}
