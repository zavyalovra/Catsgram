package ru.yandex.practicum.catsgram.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.catsgram.dto.NewPostRequest;
import ru.yandex.practicum.catsgram.dto.PostDto;
import ru.yandex.practicum.catsgram.dto.UpdatePostRequest;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMapper {

    public static Post mapToPost(NewPostRequest request) {
        Post post = new Post();
        post.setAuthorId(request.getAuthorId());
        post.setDescription(request.getDescription());
        post.setPostDate(Instant.now());

        return post;
    }

    public static PostDto mapToPostDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setAuthorId(post.getAuthorId());
        dto.setDescription(post.getDescription());
        dto.setPostDate(post.getPostDate());
        return dto;
    }

    public static Post updatePostFields(Post post, UpdatePostRequest request) {
        if (request.hasAuthorId()) {
            post.setAuthorId(request.getAuthorId());
        }
        if (request.hasDescription()) {
            post.setDescription(request.getDescription());
        }
        return post;
    }
}
