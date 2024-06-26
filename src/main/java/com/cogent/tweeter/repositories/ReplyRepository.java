package com.cogent.tweeter.repositories;

import com.cogent.tweeter.entities.Post;
import com.cogent.tweeter.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, UUID> {
    List<Reply> findAllRepliesByPostId(UUID id);
}