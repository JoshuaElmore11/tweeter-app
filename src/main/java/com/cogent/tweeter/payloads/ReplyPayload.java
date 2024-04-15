package com.cogent.tweeter.payloads;

import com.cogent.tweeter.entities.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyPayload {
    private String userName;
    private String content;
    private Set<Tag> tags;
    private UUID postId;
}
