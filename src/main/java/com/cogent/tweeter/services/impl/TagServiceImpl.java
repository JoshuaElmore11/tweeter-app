package com.cogent.tweeter.services.impl;

import com.cogent.tweeter.entities.Tag;
import com.cogent.tweeter.repositories.TagRepository;
import com.cogent.tweeter.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
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
