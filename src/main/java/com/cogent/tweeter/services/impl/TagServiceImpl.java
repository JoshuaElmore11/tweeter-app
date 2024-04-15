package com.cogent.tweeter.services.impl;

import com.cogent.tweeter.entities.Post;
import com.cogent.tweeter.entities.Reply;
import com.cogent.tweeter.entities.Tag;
import com.cogent.tweeter.repositories.PostRepository;
import com.cogent.tweeter.repositories.ReplyRepository;
import com.cogent.tweeter.repositories.TagRepository;
import com.cogent.tweeter.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> findTagsByPostId(UUID id) {
        Post post = postRepository.findById(id).get();
        return tagRepository.findTagsByPosts(post);
    }

    @Override
    public List<Tag> findTagsByReplyId(UUID id) {
        Reply reply = replyRepository.findById(id).get();
        return tagRepository.findTagsByReplies(reply);
    }

    @Override
    public boolean createTag(String name) {
        boolean check = tagRepository.existsByName(name);
        Tag tag = new Tag();

        if(!check){
            tag.setName(name);
            tagRepository.save(tag);
            return true;
        }
        return false;
    }

}
