package com.mazhar.blogs.app.controllers;

import com.mazhar.blogs.app.payloads.PostDto;
import com.mazhar.blogs.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    private ResponseEntity<PostDto> createPost(@PathVariable Integer userId,
                                               @PathVariable Integer categoryId,
                                               @RequestBody PostDto post){
        System.out.println("in controller");
        PostDto postDto = this.postService.createPost(post,userId,categoryId);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> postDto = postService.getAllPost();
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }


    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDto = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }


    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> postDto = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }


    @PutMapping("/update/{postId}/post")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable("postId") Integer postId){
       PostDto postDto1 = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}/post")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return ResponseEntity.ok("Post Deleted Successfully !!!!!!");
    }
}
