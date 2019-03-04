package com.ing.ica.alpakkakafkademo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlacksmithService {
    private static final long SWORD_CRAFTING_TIME = 2000;

    public void crearEspada() throws InterruptedException {
        Thread.sleep(SWORD_CRAFTING_TIME);
    }
}