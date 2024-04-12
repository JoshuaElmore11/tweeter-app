package com.cogent.tweeter.payloads;

import com.cogent.tweeter.entities.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {
    private String username;
    private String content;
    private String firstName;
    private String lastName;
    private Timestamp timestamp;
    private Set<Tag> tags;

}

