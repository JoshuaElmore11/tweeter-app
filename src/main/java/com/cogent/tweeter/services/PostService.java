package com.cogent.tweeter.services;

import com.cogent.tweeter.entities.Post;
import com.cogent.tweeter.payloads.PostPayload;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts();
    List<Post> findAllPostsByUsername(String username);
    Post createPost(PostPayload postPayload);
    Post findPostById(UUID id);

}
