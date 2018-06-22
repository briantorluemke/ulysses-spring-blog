package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable int id, Model model) {
        Post post = postService.findPost(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/create")
    public String makePost(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/create")
    public String viewPosts(@ModelAttribute Post post){
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editPost(
            @PathVariable int id,
            Model model
    ) {
        Post post = postService.findPost(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String sendPost(
            @ModelAttribute Post post,
            @PathVariable int id
    ){
        post.setId(id);
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(
            @PathVariable int id
    ){
        postService.delete(id);
        return "redirect:/posts";
    }
}