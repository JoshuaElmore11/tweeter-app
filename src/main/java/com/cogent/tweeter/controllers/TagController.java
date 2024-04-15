package com.cogent.tweeter.controllers;

import com.cogent.tweeter.entities.Tag;
import com.cogent.tweeter.payloads.TagPayload;
import com.cogent.tweeter.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1.0/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping()
    public ResponseEntity<?> createTag(@RequestBody TagPayload tagPayload) {
        var response = tagService.createTag(tagPayload.getName());
        if(!response) {
            return new ResponseEntity<>("Already exists", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        return new ResponseEntity<>(tagService.findAllTags(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Tag>> getAllTagsByPostId(@PathVariable UUID id) {
        return new ResponseEntity<>(tagService.findTagsByPostId(id), HttpStatus.OK);
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity<List<Tag>> getAllTagsByReplyId(@PathVariable UUID id) {
        return new ResponseEntity<>(tagService.findTagsByReplyId(id), HttpStatus.OK);
    }
}
