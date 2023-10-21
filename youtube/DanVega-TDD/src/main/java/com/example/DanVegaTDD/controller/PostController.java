package com.example.DanVegaTDD.controller;


import com.example.DanVegaTDD.PostRepository;
import com.example.DanVegaTDD.domain.Post;
import com.example.DanVegaTDD.exception.AppException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Receiver;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @GetMapping("")
    List<Post> findAll(){
        return this.postRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Post> findById(@PathVariable Integer id) {
        return Optional.ofNullable(postRepository.findById(id)
                .orElseThrow(()-> new AppException("Not Found Post", HttpStatus.NOT_FOUND)));
    }

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    Post create(@RequestBody @Valid Post post) {
        return this.postRepository.save(post);
    }

    @PostMapping(value="/update/{id}", consumes= {MediaType.APPLICATION_JSON_VALUE})
    Post update(@PathVariable("id") Integer id, @RequestBody @Valid Post paremeterPost ) {
        Optional<Post> exist = postRepository.findById(id);
        if (exist.isPresent()){
            Post post = new Post(
                    exist.get().id(),
                    exist.get().userId(),
                    exist.get().title(),
                    exist.get().body(),
                    exist.get().version()
            );
            return this.postRepository.save(post);

        } else {
            throw new AppException("Not Found Post", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
       /* Optional<Post> exist = this.postRepository.findById(id);
        if (!exist.isPresent()){
            throw new AppException("Not found Post", HttpStatus.NOT_FOUND);
        }*/
        this.postRepository.deleteById(id);
    }
}
