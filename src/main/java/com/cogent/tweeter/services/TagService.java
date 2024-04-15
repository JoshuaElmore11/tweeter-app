package com.cogent.tweeter.services;

import com.cogent.tweeter.entities.Tag;

import java.util.List;
import java.util.UUID;

public interface TagService {
    List<Tag> findAllTags();
    List<Tag> findTagsByPostId(UUID id);
    List<Tag> findTagsByReplyId(UUID id);
    boolean createTag(String name);

}
