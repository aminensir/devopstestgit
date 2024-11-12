package com.example.testeditions;

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


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestEditioNsApplicationTests {
    @Mock
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