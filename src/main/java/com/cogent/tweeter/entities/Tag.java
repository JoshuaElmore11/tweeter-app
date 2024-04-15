package com.cogent.tweeter.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLInsert;

import java.util.Set;

@Entity
@Table(name="tags")
@AllArgsConstructor
@NoArgsConstructor
@Data
//@SQLInsert(sql = "INSERT IGNORE INTO tags(name) " +
//        "VALUES (?)" )
public class Tag {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Post> posts;

    @ManyToMany(mappedBy = "replyTags", fetch = FetchType.EAGER)
    private Set<Reply> replies;

}
