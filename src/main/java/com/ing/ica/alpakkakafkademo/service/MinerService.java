package com.ing.ica.alpakkakafkademo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MinerService {
    private static final long MINING_TIME = 2000;

    public void obtenerMinerales() throws InterruptedException {
        Thread.sleep(MINING_TIME);
    }
}