package com.example.testeditions;

import com.example.testeditions.Entites.AnnonceColocation;
import com.example.testeditions.Entites.Preferences;
import com.example.testeditions.Entites.TypeLocal;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Repositories.AnnonceColocationRepository;
import com.example.testeditions.Repositories.UserRepository;
import com.example.testeditions.Services.AnnonceColocationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestEditioNsApplicationTests {

    @Mock
    private AnnonceColocationRepository annonceColocationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AnnonceColocationImpl annonceColocationService;

    private AnnonceColocation annonceColocation;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation d'une annonce de colocation
        annonceColocation = new AnnonceColocation();
        annonceColocation.setId(1L);
        annonceColocation.setTitre("Annonce Test");
        annonceColocation.setDescription("Description Test");

        // Initialisation d'un utilisateur avec des préférences
        user = new User();
        user.setId(1L);

        // Assurez-vous d'initialiser la préférence avec un setter ou un constructeur approprié
        Preferences preference = new Preferences();
        preference.setSelectedTypes(Arrays.asList(TypeLocal.Studio.name()));  // Utilisez le setter si nécessaire
        user.setPreferences(Arrays.asList(preference));
    }

    @Test
    void testGetAllAnnonces() {
        List<AnnonceColocation> annonces = Arrays.asList(annonceColocation);
        when(annonceColocationRepository.findAll()).thenReturn(annonces);

        List<AnnonceColocation> result = annonceColocationService.getAllAnnonces();

        assertEquals(annonces.size(), result.size());
        verify(annonceColocationRepository, times(1)).findAll();
    }

    @Test
    void testGetAnnonceById() {
        when(annonceColocationRepository.findById(1L)).thenReturn(Optional.of(annonceColocation));

        Optional<AnnonceColocation> result = annonceColocationService.getAnnonceById(1L);

        assertTrue(result.isPresent());
        assertEquals(annonceColocation, result.get());
        verify(annonceColocationRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateAnnonce() {
        when(annonceColocationRepository.save(annonceColocation)).thenReturn(annonceColocation);

        AnnonceColocation result = annonceColocationService.createAnnonce(annonceColocation);

        assertNotNull(result);
        assertEquals(annonceColocation, result);
        verify(annonceColocationRepository, times(1)).save(annonceColocation);
    }

    @Test
    void testUpdateAnnonce() {
        AnnonceColocation updatedAnnonce = new AnnonceColocation();
        updatedAnnonce.setTitre("Updated Title");
        when(annonceColocationRepository.findById(1L)).thenReturn(Optional.of(annonceColocation));
        when(annonceColocationRepository.save(any(AnnonceColocation.class))).thenReturn(annonceColocation);

        AnnonceColocation result = annonceColocationService.updateAnnonce(1L, updatedAnnonce);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitre());
        verify(annonceColocationRepository, times(1)).findById(1L);
        verify(annonceColocationRepository, times(1)).save(any(AnnonceColocation.class));
    }

    @Test
    void testDeleteAnnonce() {
        doNothing().when(annonceColocationRepository).deleteById(1L);

        annonceColocationService.deleteAnnonce(1L);

        verify(annonceColocationRepository, times(1)).deleteById(1L);
    }

    @Test
    void testSearchAnnonces() {
        String query = "Test";
        List<AnnonceColocation> annonces = Arrays.asList(annonceColocation);
        when(annonceColocationRepository.findByTitreContainingIgnoreCase(query)).thenReturn(annonces);

        List<AnnonceColocation> result = annonceColocationService.searchAnnonces(query);

        assertEquals(annonces.size(), result.size());
        verify(annonceColocationRepository, times(1)).findByTitreContainingIgnoreCase(query);
    }

    @Test
    void testGetAnnoncesByUserId() {
        List<AnnonceColocation> annonces = Arrays.asList(annonceColocation);
        when(annonceColocationRepository.findByUser_Id(1L)).thenReturn(annonces);

        List<AnnonceColocation> result = annonceColocationService.getAnnoncesByUserId(1L);

        assertEquals(annonces.size(), result.size());
        verify(annonceColocationRepository, times(1)).findByUser_Id(1L);
    }

    @Test
    void testGetAnnoncesSelonPreferences() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(annonceColocationRepository.findByType(TypeLocal.Studio)).thenReturn(Arrays.asList(annonceColocation));

        List<AnnonceColocation> result = annonceColocationService.getAnnoncesSelonPreferences(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(userRepository, times(1)).findById(1L);
        verify(annonceColocationRepository, times(1)).findByType(TypeLocal.Studio);
    }
}
