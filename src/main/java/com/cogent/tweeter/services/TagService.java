package com.cogent.tweeter.services;

import com.cogent.tweeter.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();
    boolean createTag(String name);

}
