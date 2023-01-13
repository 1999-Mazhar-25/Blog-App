package com.mazhar.blogs.app.repositories;

import com.mazhar.blogs.app.entities.Category;
import com.mazhar.blogs.app.entities.Post;
import com.mazhar.blogs.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {


    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
