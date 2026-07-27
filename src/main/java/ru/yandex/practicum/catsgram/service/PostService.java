package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.dal.PostRepository;
import ru.yandex.practicum.catsgram.dto.NewPostRequest;
import ru.yandex.practicum.catsgram.dto.PostDto;
import ru.yandex.practicum.catsgram.dto.UpdatePostRequest;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.mapper.PostMapper;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(NewPostRequest request) {
        if (request.getAuthorId() == null) {
            throw new ConditionsNotMetException("Должен быть указан ID автора поста");
        }

        Post post = PostMapper.mapToPost(request);
        post = postRepository.save(post);

        return PostMapper.mapToPostDto(post);
    }

    public PostDto getPostById(long postId) {
        return postRepository.findById(postId)
                .map(PostMapper::mapToPostDto)
                .orElseThrow(() -> new NotFoundException("Пост не найден с ID: " + postId));
    }

    public List<PostDto> getPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    public PostDto updatePost(long postId, UpdatePostRequest request) {
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new ConditionsNotMetException("Текст публикации не может быть пустым");
        }

        Post updatedPost = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Пост с идентификатором " + postId + " не найден."));

        updatedPost.setDescription(request.getDescription());
        updatedPost.setPostDate(Instant.now());

        postRepository.update(updatedPost);

        return PostMapper.mapToPostDto(updatedPost);
    }
}