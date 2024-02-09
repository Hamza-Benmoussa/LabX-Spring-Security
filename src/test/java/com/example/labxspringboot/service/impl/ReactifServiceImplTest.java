package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.ReactifDto;
import com.example.labxspringboot.service.IReactifService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

    @SpringBootTest
    @ExtendWith(SpringExtension.class)
    @Transactional
    public class ReactifServiceImplTest {

        @Autowired
        private IReactifService iReactifService;

        @ParameterizedTest
        @CsvFileSource(resources = "/listreactif.csv", numLinesToSkip = 1)
        void saveReactif(String name, String description, int quantiteStock, String dateExpiration, String fournisseurNom) {
            ReactifDto reactifDto = new ReactifDto();
            reactifDto.setNom(name);
            reactifDto.setDescription(description);
            reactifDto.setQuantiteStock(quantiteStock);
            reactifDto.setDateExpiration(dateExpiration);
            reactifDto.setFournisseurNom(fournisseurNom);
            reactifDto.setDeleted(false);

            iReactifService.saveReactif(reactifDto); // Corrected line
        }




























//    @Autowired
//    private IReactifService reactifService;
//
//    private Reactif testReactif;
//
//    @BeforeEach
//    void setUp() {
//        // Create a new Reactif before each test
//        testReactif = new Reactif();
//        testReactif.setNom("TestReactif");
//        testReactif.setQuantiteStock(10);
//        testReactif.setDateExpiration("2025-01-01");
//
//        // Save the Reactif
//        reactifService.saveReactif(testReactif);
//    }
//
//    @AfterEach
//    void tearDown() {
//        if (reactifService.getReactifById(testReactif.getId()) != null) {
//            reactifService.deleteReactif(testReactif.getId());
//        }
//    }
//    @Test
//    void saveReactif() {
//        assertNotNull(testReactif.getId(), "Reactif ID should not be null after saving");
//    }
//    @Test
//    void getReactifById() {
//        Reactif retrievedReactif = reactifService.getReactifById(testReactif.getId());
//        assertNotNull(retrievedReactif, "Retrieved reactif should not be null");
//        assertEquals(testReactif.getId(), retrievedReactif.getId(), "IDs should match");
//    }
//    @Test
//    void updateReactif() {
//        assertNotNull(testReactif.getId(), "Reactif ID should not be null before updating");
//        testReactif.setQuantiteStock(20);
//        Reactif updatedReactif = reactifService.updateReactif(testReactif ,testReactif.getId());
//        assertNotNull(updatedReactif, "Updated reactif should not be null");
//        assertEquals(20, updatedReactif.getQuantiteStock(), "QuantiteStockReactif should be updated");
//    }
//    @Test
//    void getAllReactifs() {
//        List<Reactif> reactifs = reactifService.getReactifs();
//        assertFalse(reactifs.isEmpty());
//    }
//    @Test
//    void deleteReactif() {
//        reactifService.deleteReactif(testReactif.getId());
//        assertNull(reactifService.getReactifById(testReactif.getId()), "Reactif should be deleted");
//    }
    }
