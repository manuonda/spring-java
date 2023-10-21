package com.example.DanVegaTDD.controller;

import com.example.DanVegaTDD.PostRepository;
import com.example.DanVegaTDD.domain.Post;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// test junit https://www.youtube.com/watch?v=kXhYu939_5s
@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PostRepository postRepository;

    List<Post> posts = new ArrayList<>();

    @BeforeEach
    void setUp(){
        posts = List.of(
                new Post(1 ,1,"Hello Wowrld!","This is my first post ", null),
                new Post(2 ,1,"Hello Wowrld 2d!","This is my first post ", null)
        );
    }

    @Test
    @DisplayName("Test find all")
    public void given_whenfindAllPosts_thenReturnPosts() throws Exception {
        String jsonResponse = """
                [
                    {
                        "id":1,
                        "userId":1,
                        "title":"Hello Wowrld!",
                        "body":"This is my first post ", 
                        "version":null
                    },
                    {
                        "id":2 ,
                        "userId":1,
                        "title":"Hello Wowrld 2d!",
                        "body":"This is my first post ", 
                        "version":null
                    }
                ]
                """;
        //when
        when(this.postRepository.findAll()).thenReturn(posts);

        //then
        ResultActions resultActions = mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));

        JSONAssert.assertEquals(jsonResponse, resultActions.andReturn().getResponse().getContentAsString(), false);
    }

    @Test
    @DisplayName("Get Post By Id")
    void shouldSavePostWhenGivenValidId() throws Exception {
        Post post = new Post(1,1,"Test Title", "Test Body",null);
        System.out.println(post.toString());
        when(this.postRepository.findById(1)).thenReturn(Optional.of(post));
        String json = """
                {
                    "id": %d,
                    "userId": %d,
                    "title": '%s' ,
                    "body": '%s',
                    "version": null
                }
                """.formatted(post.id(),post.userId(),post.title(),post.body());
        System.out.println("json :" +json);
        mockMvc.perform(get("/api/v1/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    @DisplayName("Not Found Post invalid id")
    void shouldFindPost_WhenGivenInvalidId() throws Exception {
        Post post = new Post(1,1,"Test Title", "Test Body",null);
        String json = """
                {
                    "id": %d,
                    "userId": %d,
                    "title": '%s' ,
                    "body": '%s',
                    "version": null
                }
                """.formatted(post.id(),post.userId(),post.title(),post.body());
        when(this.postRepository.save(post)).thenReturn(post);
        mockMvc.perform(get("/api/v1/posts/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create Post Save")
    void shouldCreateWhenSendPostIsValid() throws Exception {
        //given
        var post = new Post(1, 1, "Title one", "Body one", null);

        //when
        when(this.postRepository.save(post)).thenReturn(post);
        String jsonData = """
                {
                    "id": %d,
                    "userId": %d,
                    "title": "%s" ,
                    "body": "%s",
                    "version": null
                }
                """.formatted(post.id(),post.userId(),post.title(),post.body());

        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/posts/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonData))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Not Create Post Invalid")
    void shouldCreateWhenSendPostIsInvalid() throws Exception {
        //given
        var post = new Post(1, 1, "", "", null);

        //when
        when(this.postRepository.save(post)).thenReturn(post);
        String jsonData = """
                {
                    "id": %d,
                    "userId": %d,
                    "title": "%s" ,
                    "body": "%s",
                    "version": null
                }
                """.formatted(post.id(),post.userId(),post.title(),post.body());

        ResultActions resultActions = mockMvc.perform(
                        post("/api/v1/posts/create")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonData))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("Update Post ")
    void shouldUPdate_whenSavePost_returnObjectPost() throws Exception {
       var update = new Post(1, 1 , "title one","body one", null);
       when(this.postRepository.findById(1)).thenReturn(Optional.of(update));
       when(this.postRepository.save(update)).thenReturn(update);

        String jsonData = """
                {
                    "id": %d,
                    "userId": %d,
                    "title": "%s" ,
                    "body": "%s",
                    "version": null
                }
                """.formatted(update.id(),update.userId(),update.title(),update.body());

        mockMvc.perform(post("/api/v1/posts/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete post by id")
    void shoulDeletePost_whenFindPost_returnEmptyObject() throws Exception{
        //given
       // var post = new Post(1,1,"title one","body one", null);
        //when(this.postRepository.save(post)).thenReturn(post);
        doNothing().when(postRepository).deleteById(1);

        mockMvc.perform(delete("/api/v1/posts/delete/1"))
                .andExpect(status().isNoContent());

        verify(postRepository, times(1)).deleteById(1);
    }
}
