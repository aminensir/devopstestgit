package com.example.testeditions;

<<<<<<< Updated upstream
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

=======
import com.example.testeditions.Entites.TypeLocale;
import com.example.testeditions.Repositories.AnnonceAirbnbRepository;
import com.example.testeditions.Services.AnnAirbnbImpl;
import com.example.testeditions.Entites.AnnonceAirbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


>>>>>>> Stashed changes
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestEditioNsApplicationTests {
    @Mock
<<<<<<< Updated upstream
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
=======
    AnnonceAirbnbRepository annonceAirbnbRepository;

    @InjectMocks
    AnnAirbnbImpl annAirbnb;

    AnnonceAirbnb annonceAirbnb = new AnnonceAirbnb(
            1L,                             // id
            "Appartement au centre ville",   // titre
            "Description du logement",       // description
            new Date(),                      // date_annonce
            "image.jpg",                     // image
            150.0f,                          // prix
            10,                              // llike
            "123 rue de Paris",              // adresse
            3,                               // nbr_chambres
            TypeLocale.LUXE,                 // Type (using LUXE from the enum)
            new ArrayList<>()                // reservationAirbnbs (empty list for now)
    );




    @Test
    public void getAnnouncementById() {
        when(annonceAirbnbRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(annonceAirbnb));

        AnnonceAirbnb retriveann = annAirbnb.getAnnouncementById(1L);

        Assertions.assertNotNull(retriveann);
        Assertions.assertEquals("Description du logement", retriveann.getDescription()); // Updated expected value
    }

}
>>>>>>> Stashed changes
