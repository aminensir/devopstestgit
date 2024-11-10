package com.example.testeditions;

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

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestEditioNsApplicationTests {
    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostServiceImpl postService;

    Post post = new Post(1L, "Description du post", new Date(), new User(), null, null);

    @Test
    public void getAllPosts() {
        // Exemple de structure pour les tests de récupération de tous les posts
        // Implémentez la logique de votre test ici si nécessaire
    }

    @Test
    public void getPostById() {
        // Simulation de la méthode `findById` du repository
        when(postRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(post));

        // Appel du service pour obtenir le post par ID
        Post retrievedPost = postService.getPostById(1L);

        // Vérifications
        Assertions.assertNotNull(retrievedPost);
        Assertions.assertEquals("Description du post", retrievedPost.getDescriptionPost());
    }

    @Test
    public void createPost() {
        // Exemple de test pour la création d'un post (à implémenter selon votre logique métier)
    }
}
