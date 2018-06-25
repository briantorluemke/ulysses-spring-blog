package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Post, Long>{

}