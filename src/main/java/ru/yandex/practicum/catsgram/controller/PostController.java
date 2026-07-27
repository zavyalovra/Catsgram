package ru.yandex.practicum.catsgram.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.dto.NewPostRequest;
import ru.yandex.practicum.catsgram.dto.PostDto;
import ru.yandex.practicum.catsgram.dto.UpdatePostRequest;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody NewPostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@PathVariable("postId") long postId, @RequestBody UpdatePostRequest request) {
        return postService.updatePost(postId, request);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPostById(@PathVariable("postId") long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPosts() {
        return postService.getPosts();
    }
}