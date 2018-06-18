package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String posts() {
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable int id) {
        Post post = new Post("Cats are great.", "Just great.");
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public @ResponseBody String makePost(){
        return "Forms for creating a post";
    }

    @PostMapping("/posts/create")
    public String viewPosts(){
        return "Here is where you make a post";
    }

}
