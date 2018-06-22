package com.codeup.blog.services;

import com.codeup.blog.models.Post;
import com.codeup.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postDao;

    public PostService(PostRepository postDao) {
        this.postDao = postDao;
    }


    public Iterable<Post> findAll() {
        return postDao.findAll();
    }

    public Post findPost(long id) {
        return postDao.findOne(id);
    }

    public void save(Post post) {
        postDao.save(post);
    }

    public void delete(long id) {
        postDao.delete(id);
    }
}