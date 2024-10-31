package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.CommentDto;
import net.javaguides.springboot.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postUrl}/comments")
    public String createComment (
        @PathVariable("postUrl") String postUrl,
        @ModelAttribute("comment") CommentDto comment,
        Model model
    ) {
        commentService.createComment(postUrl, comment);
        return "redirect:/post/" + postUrl;
    }
}
