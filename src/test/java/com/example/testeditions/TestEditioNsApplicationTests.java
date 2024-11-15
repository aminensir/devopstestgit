package com.example.testeditions;

import com.example.testeditions.Entites.Reclamation;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Repositories.ReclamationRepository;
import com.example.testeditions.Repositories.UserRepository;
import com.example.testeditions.Services.ReclamationImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestEditioNsApplicationTests {

    @Mock
    private ReclamationRepository reclamationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReclamationImpl reclamationService;

    @Test
    void testSaveReclamation() {
        Reclamation reclamation = new Reclamation();
        reclamation.setId_reclamation(1);
        reclamation.setCategorie_reclamation("Category 1");

        when(reclamationRepository.save(Mockito.any(Reclamation.class))).thenReturn(reclamation);

        Reclamation savedReclamation = reclamationService.save(reclamation);

        Assertions.assertNotNull(savedReclamation);
        Assertions.assertEquals(1, savedReclamation.getId_reclamation());
    }

    @Test
    void testDeleteReclamation() {
        doNothing().when(reclamationRepository).deleteById(1);

        reclamationService.delete(1);

        verify(reclamationRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateReclamation() {
        Reclamation existingReclamation = new Reclamation();
        existingReclamation.setId_reclamation(1);
        existingReclamation.setCategorie_reclamation("Old Category");

        Reclamation updatedReclamation = new Reclamation();
        updatedReclamation.setId_reclamation(1);
        updatedReclamation.setCategorie_reclamation("Updated Category");

        when(reclamationRepository.findById(1)).thenReturn(Optional.of(existingReclamation));
        when(reclamationRepository.save(Mockito.any(Reclamation.class))).thenReturn(updatedReclamation);

        Reclamation result = reclamationService.update(updatedReclamation);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Updated Category", result.getCategorie_reclamation());
    }

    @Test
    void testAddReclamationAndAssignToUser() {
        User user = new User();
        user.setId(1L);

        Reclamation reclamation = new Reclamation();
        reclamation.setId_reclamation(1);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(reclamationRepository.save(Mockito.any(Reclamation.class))).thenReturn(reclamation);

        Reclamation savedReclamation = reclamationService.addReclamationAndAssignToUser(reclamation, 1L);

        Assertions.assertNotNull(savedReclamation);
        Assertions.assertEquals(1, savedReclamation.getId_reclamation());
        verify(reclamationRepository, times(1)).save(reclamation);
    }

    @Test
    void testGetReclamationsByUserId() {
        Reclamation reclamation1 = new Reclamation();
        reclamation1.setId_reclamation(1);

        Reclamation reclamation2 = new Reclamation();
        reclamation2.setId_reclamation(2);

        when(reclamationRepository.findByUserId(1L)).thenReturn(Arrays.asList(reclamation1, reclamation2));

        var reclamations = reclamationService.getReclamationsByUserId(1L);

        Assertions.assertNotNull(reclamations);
        Assertions.assertEquals(2, reclamations.size());
    }

    @Test
    void testGetReclamationById() {
        Reclamation reclamation = new Reclamation();
        reclamation.setId_reclamation(1);

        when(reclamationRepository.findById(1)).thenReturn(Optional.of(reclamation));

        Reclamation result = reclamationService.getReclamationById(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getId_reclamation());
    }

    @Test
    void testUpdateReclamationState() {
        Reclamation existingReclamation = new Reclamation();
        existingReclamation.setId_reclamation(1);
        existingReclamation.setEtat_reclamation(0);

        when(reclamationRepository.findById(1)).thenReturn(Optional.of(existingReclamation));
        when(reclamationRepository.save(Mockito.any(Reclamation.class))).thenReturn(existingReclamation);

        Reclamation result = reclamationService.updateReclamationState(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getEtat_reclamation());
        verify(reclamationRepository, times(1)).save(existingReclamation);
    }
}
