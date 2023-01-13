package com.mazhar.blogs.app.services.impl;

import com.mazhar.blogs.app.entities.Category;
import com.mazhar.blogs.app.entities.Post;
import com.mazhar.blogs.app.entities.User;
import com.mazhar.blogs.app.exceptions.ResourceNotFoundException;
import com.mazhar.blogs.app.payloads.PostDto;
import com.mazhar.blogs.app.repositories.CategoryRepo;
import com.mazhar.blogs.app.repositories.PostRepo;
import com.mazhar.blogs.app.repositories.UserRepo;
import com.mazhar.blogs.app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postdto,Integer userId,Integer categoryId) {
        System.out.println("in service");

        User user =this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user","userId",userId));

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category",
                        "categoryId",categoryId));
        System.out.println("after category");
        Post post = this.modelMapper.map(postdto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
       Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postdto, Integer postId) {
        Post post =this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post","post Id",postId));

        post.setTitle(postdto.getTitle());
        post.setContent(postdto.getContent());
        post.setImageName(postdto.getImageName());

        Post updatedPost = this.postRepo.save(post);

        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post","post Id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDto = posts.stream()
                .map(post ->this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        return postDto;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post","post Id",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
       Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category","Cateory Id",categoryId));
       List<Post> posts = this.postRepo.findByCategory(cat);
      List<PostDto> postDto = posts.stream()
               .map(post ->this.modelMapper.map(post,PostDto.class))
                       .collect(Collectors.toList());
        return postDto;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user","user Id",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDto = posts.stream()
                .map(post ->this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        return postDto;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }
}
