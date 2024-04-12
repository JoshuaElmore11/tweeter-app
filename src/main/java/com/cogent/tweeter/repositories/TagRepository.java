package com.cogent.tweeter.repositories;

import com.cogent.tweeter.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Boolean existsByName(String name);
}
