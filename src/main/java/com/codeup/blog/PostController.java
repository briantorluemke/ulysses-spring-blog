package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    public @ResponseBody String posts() {
        return "This is the index page of the blog.";
    }

    @GetMapping("/posts/{id}")
    public @ResponseBody String viewPost(@PathVariable int id) {
        return "Viewing number " + id;
    }

    @GetMapping("/posts/create")
    public @ResponseBody String makePost(){
        return "Forms for creating a post";
    }

    @PostMapping("/posts/create")
    public @ResponseBody String makePost(){
        return "Here is where you make a post";
    }
}
