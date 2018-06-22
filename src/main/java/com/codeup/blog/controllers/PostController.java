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

    @GetMapping("/seed")
    public String seedPosts() {
        postService.save(new Post("Choose the perfect design", "Create a beautiful blog that fits your style. Choose from a selection of easy-to-use templates – all with flexible layouts and hundreds of background images – or design something new."));
        postService.save(new Post("Get a free domain", "Give your blog the perfect home. Get a free blogspot.com domain or buy a custom domain with just a few clicks."));
        postService.save(new Post("Earn money", "Get paid for your hard work. Google AdSense can automatically display relevant targeted ads on your blog so that you can earn income by posting about your passion."));
        postService.save(new Post("Know your audience", "Find out which posts are a hit with Blogger’s built-in analytics. You’ll see where your audience is coming from and what they’re interested in. You can even connect your blog directly to Google Analytics for a more detailed look."));
        postService.save(new Post("Hang onto your memories", "Save the moments that matter. Blogger lets you safely store thousands of posts, photos, and more with Google for free."));
        postService.save(new Post("Join millions of others", "Whether sharing your expertise, breaking news, or whatever’s on your mind, you’re in good company on Blogger. Sign up to discover why millions of people have published their passions here."));
        return "redirect:/posts";
    }
}