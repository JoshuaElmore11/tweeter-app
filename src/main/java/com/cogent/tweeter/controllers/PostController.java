package com.cogent.tweeter.controllers;

import com.cogent.tweeter.entities.Post;
import com.cogent.tweeter.payloads.PostPayload;
import com.cogent.tweeter.services.PostService;
import com.cogent.tweeter.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1.0/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostPayload postPayload){
        var data = postService.createPost(postPayload);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findAllPostsByUsername(@PathVariable String username) {
        var data = postService.findAllPostsByUsername(username);
        if(data == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPostById(@PathVariable UUID id) {
        var data = postService.findPostById(id);
        if(data==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
