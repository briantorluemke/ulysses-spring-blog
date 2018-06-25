package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.repositories.UserRepository;
import com.codeup.blog.services.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/posts")
public class PostController {
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public PostController(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public String posts(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable int id, Model model) {
        Post post = postRepo.findOne((long) id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/create")
    public String makePost(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/create")
    public String postsInsert(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setOwner(user);
        postRepo.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editPost(
            @PathVariable int id,
            Model model
    ) {
        Post post = postRepo.findOne((long) id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String sendPost(
            @ModelAttribute Post post,
            @PathVariable int id
    ){
        post.setId(id);
        postRepo.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(
            @PathVariable int id
    ){
        postRepo.delete((long) id);
        return "redirect:/posts";
    }

    @GetMapping("/seed")
    public String seedPosts() {
        postRepo.save(new Post("Choose the perfect design", "Create a beautiful blog that fits your style. Choose from a selection of easy-to-use templates – all with flexible layouts and hundreds of background images – or design something new.", userRepo.findOne((long) 1)));
        postRepo.save(new Post("Get a free domain", "Give your blog the perfect home. Get a free blogspot.com domain or buy a custom domain with just a few clicks.", userRepo.findOne((long) 1)));
        postRepo.save(new Post("Earn money", "Get paid for your hard work. Google AdSense can automatically display relevant targeted ads on your blog so that you can earn income by posting about your passion.", userRepo.findOne((long) 1)));
        postRepo.save(new Post("Know your audience", "Find out which posts are a hit with Blogger’s built-in analytics. You’ll see where your audience is coming from and what they’re interested in. You can even connect your blog directly to Google Analytics for a more detailed look.", userRepo.findOne((long) 1)));
        postRepo.save(new Post("Hang onto your memories", "Save the moments that matter. Blogger lets you safely store thousands of posts, photos, and more with Google for free.", userRepo.findOne((long) 1)));
        postRepo.save(new Post("Join millions of others", "Whether sharing your expertise, breaking news, or whatever’s on your mind, you’re in good company on Blogger. Sign up to discover why millions of people have published their passions here.", userRepo.findOne((long) 1)));
        return "redirect:/posts";
    }
}