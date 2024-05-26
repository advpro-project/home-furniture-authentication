package com.hoomgroom.authentication.controller;

import com.hoomgroom.authentication.service.SeedService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SeedControllerTest {

    @Mock
    private SeedService seedService;

    @InjectMocks
    private SeedController seedController;

    public SeedControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSeed_Success() throws ExecutionException, InterruptedException {
        when(seedService.seed()).thenReturn(CompletableFuture.completedFuture(true));

        ResponseEntity<String> responseEntity = seedController.seed().get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Berhasil seeding data", responseEntity.getBody());
    }

    @Test
    void testSeed_Failure() throws ExecutionException, InterruptedException {
        when(seedService.seed()).thenReturn(CompletableFuture.completedFuture(false));

        ResponseEntity<String> responseEntity = seedController.seed().get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Gagal seeding data", responseEntity.getBody());
    }
}
