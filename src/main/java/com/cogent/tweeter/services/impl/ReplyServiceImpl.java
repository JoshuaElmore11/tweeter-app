package com.cogent.tweeter.services.impl;

import com.cogent.tweeter.entities.Post;
import com.cogent.tweeter.entities.Reply;
import com.cogent.tweeter.entities.Tag;
import com.cogent.tweeter.entities.User;
import com.cogent.tweeter.payloads.ReplyPayload;
import com.cogent.tweeter.repositories.PostRepository;
import com.cogent.tweeter.repositories.ReplyRepository;
import com.cogent.tweeter.repositories.TagRepository;
import com.cogent.tweeter.repositories.UserRepository;
import com.cogent.tweeter.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostRepository postRepository;


    @Override
    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    @Override
    public List<Reply> findAllRepliesByPostId(UUID id) {
        return replyRepository.findAllRepliesByPostId(id);
    }

    @Override
    public Reply createReply(ReplyPayload replyPayload) {
        Set<Tag> tags = replyPayload.getTags();
        for( Tag t: tags) {
            if(!tagRepository.existsByName(t.getName())){
                Tag tag = new Tag();
                tag.setName(t.getName());
                tagRepository.save(tag);
            }
        }
        User user = userRepository.findByUsername(replyPayload.getUserName()).get();
        Post post = postRepository.findById(replyPayload.getPostId()).get();
        Reply reply = new Reply();
        reply.setId(UUID.randomUUID());
        reply.setContent(replyPayload.getContent());
        reply.setReplyTags(replyPayload.getTags());
        reply.setUser(user);
        reply.setPost(post);
        return replyRepository.save(reply);
    }

    @Override
    public Reply findReplyById(UUID id) {
        Optional<Reply> replyOptional = replyRepository.findById(id);
        Reply reply = new Reply();

        if(replyOptional.isPresent()){
            reply = replyOptional.get();
        }
        return reply;
    }
}
