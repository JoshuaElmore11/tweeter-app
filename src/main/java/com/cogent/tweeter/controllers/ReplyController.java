package com.cogent.tweeter.controllers;

import com.cogent.tweeter.entities.Reply;
import com.cogent.tweeter.payloads.ReplyPayload;
import com.cogent.tweeter.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1.0/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping
    public ResponseEntity<Reply> createReply(@RequestBody ReplyPayload replyPayload) {
        var data = replyService.createReply(replyPayload);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reply>> findAllRepliesByPostId(@PathVariable UUID id) {
        var data = replyService.findAllRepliesByPostId(id);
        if(data == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Reply> findReplyById(@PathVariable UUID id){
        var data = replyService.findReplyById(id);
        if(data == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
