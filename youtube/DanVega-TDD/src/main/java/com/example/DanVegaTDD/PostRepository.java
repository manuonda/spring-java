package com.example.DanVegaTDD;

import com.example.DanVegaTDD.domain.Post;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface PostRepository extends ListCrudRepository<Post, Integer> {
    Optional<Post> findById(Integer id);
}
