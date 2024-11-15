package com.example.testeditions;

import com.example.testeditions.Repositories.UserRepository;
import com.example.testeditions.Services.PostServiceImpl;
import com.example.testeditions.Entites.Post;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Repositories.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestEditioNsApplicationTests {

    @Mock
    PostRepository postRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    PostServiceImpl postService;

    // Example Post instance used across tests
    Post post = new Post(1L, "Description du post", new Date(), new User(), null, null);

    @Test
    public void getAllPosts() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(post));

        var posts = postService.getAllPosts();

        Assertions.assertNotNull(posts);
        Assertions.assertEquals(1, posts.size());
        Assertions.assertEquals("Description du post", posts.get(0).getDescriptionPost());
    }

    @Test
    public void getPostById() {
        when(postRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(post));

        Post retrievedPost = postService.getPostById(1L);

        Assertions.assertNotNull(retrievedPost);
        Assertions.assertEquals("Description du post", retrievedPost.getDescriptionPost());
    }

    @Test
    public void createPost() {
        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        Post createdPost = postService.createPost(post);

        Assertions.assertNotNull(createdPost);
        Assertions.assertEquals("Description du post", createdPost.getDescriptionPost());
    }

    @Test
    public void getAveragePostsPerUser() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(postRepository.count()).thenReturn(4L);

        double average = postService.getAveragePostsPerUser();

        Assertions.assertEquals(2.0, average);
    }
}
