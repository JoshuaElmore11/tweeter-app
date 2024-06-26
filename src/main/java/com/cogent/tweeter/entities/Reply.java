package com.cogent.tweeter.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="replies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reply {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String content;
    @CreationTimestamp
    private Timestamp created;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "replies_tags",
            joinColumns = @JoinColumn(
                    name = "reply_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Tag> replyTags;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="post_id",
            nullable = false
    )
    private Post post;
}
