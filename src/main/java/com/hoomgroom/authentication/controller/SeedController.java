package com.hoomgroom.authentication.controller;

import com.hoomgroom.authentication.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/seed")
public class SeedController {
    @Autowired
    private SeedService seedService;

    @GetMapping("")
    public CompletableFuture<ResponseEntity<String>> seed() {
        return seedService.seed()
                .thenApply(isSeeded -> {
                    if (isSeeded) {
                        return ResponseEntity.ok("Berhasil seeding data");
                    } else {
                        return ResponseEntity.ok("Gagal seeding data");
                    }
                });
    }
}
