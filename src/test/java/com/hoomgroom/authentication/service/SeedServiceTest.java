package com.hoomgroom.authentication.service;

import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SeedServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SeedService seedService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSeedSuccess() {
        CompletableFuture<Boolean> result = seedService.seed();

        Boolean isSeeded = result.join();
        verify(userRepository, times(SeedService.SEED_COUNT)).save(any(User.class));

        assertTrue(isSeeded);
    }

    @Test
    void testSeedFailure() {
        doThrow(new IllegalArgumentException()).when(userRepository).save(any(User.class));

        CompletableFuture<Boolean> result = seedService.seed();
        Boolean isSeeded = result.join();
        verify(userRepository, atLeastOnce()).save(any(User.class));

        assertFalse(isSeeded);
    }

    @Test
    void testUserDetails() {
        seedService.seed().join();

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(SeedService.SEED_COUNT)).save(userCaptor.capture());

        List<User> savedUsers = userCaptor.getAllValues();
        assertEquals(SeedService.SEED_COUNT, savedUsers.size());

        for (int i = 0; i < SeedService.SEED_COUNT; i++) {
            User user = savedUsers.get(i);
            assertNotNull(user.getFullName());
            assertNotNull(user.getDateOfBirth());
            assertNotNull(user.getGender());
            assertNotNull(user.getUsername());
            assertNotNull(user.getEmail());
            assertNotNull(user.getPassword());
            assertNotNull(user.getRole());
            assertTrue(user.getWalletBalance() >= 0);
        }
    }
}
