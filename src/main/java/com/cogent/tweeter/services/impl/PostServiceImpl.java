package com.cogent.tweeter.services.impl;

import com.cogent.tweeter.entities.Post;
import com.cogent.tweeter.entities.Tag;
import com.cogent.tweeter.entities.User;
import com.cogent.tweeter.payloads.PostPayload;
import com.cogent.tweeter.repositories.PostRepository;
import com.cogent.tweeter.repositories.TagRepository;
import com.cogent.tweeter.repositories.UserRepository;
import com.cogent.tweeter.services.PostService;
import com.cogent.tweeter.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllPostsByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        return postRepository.findAllPostsByUserId(user.getId());
    }

    @Override
    public Post createPost(PostPayload postPayload) {
        //check tags for existing if not create
//        Set<Tag> tags = postPayload.getTags();
//        for( Tag t: tags) {
//            if(!tagRepository.existsByName(t.getName())){
//                Tag tag = new Tag();
//                tag.setName(t.getName());
//                tagRepository.save(tag);
//            }
//        }
        //find post's users
        User user = userRepository.findByUsername(postPayload.getUsername()).get();
        //create new post
        Post post = new Post();
        //assign UUID
        post.setId(UUID.randomUUID());
        //save info from payload
        post.setContent(postPayload.getContent());
//        post.setTimestamp(LocalTime.now(ZoneId.of("GMT-06:00")));
        post.setTags(postPayload.getTags());
        post.setUser(user);

        return postRepository.save(post);
    }

    @Override
    public Post findPostById(UUID id) {
        Optional<Post> postOptional = postRepository.findById(id);
        Post post = new Post();

        if(postOptional.isPresent()) {
            post = postOptional.get();
        }

        return post;
    }
}
