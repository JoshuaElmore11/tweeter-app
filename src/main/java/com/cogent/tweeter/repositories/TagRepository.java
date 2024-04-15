package com.cogent.tweeter.repositories;

import com.cogent.tweeter.entities.Post;
import com.cogent.tweeter.entities.Reply;
import com.cogent.tweeter.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Boolean existsByName(String name);
    List<Tag> findTagsByPosts(Post post);
    List<Tag> findTagsByReplies(Reply reply);
}
