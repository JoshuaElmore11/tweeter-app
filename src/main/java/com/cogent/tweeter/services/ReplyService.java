package com.cogent.tweeter.services;

import com.cogent.tweeter.entities.Post;
import com.cogent.tweeter.entities.Reply;
import com.cogent.tweeter.payloads.ReplyPayload;

import java.util.List;
import java.util.UUID;

public interface ReplyService {
    List<Reply> getAllReplies();
    List<Reply> findAllRepliesByPostId(UUID id);
    Reply createReply(ReplyPayload replyPayload);
    Reply findReplyById(UUID id);
}
