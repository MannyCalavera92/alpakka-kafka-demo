package com.ing.ica.alpakkakafkademo.actors;

import akka.actor.UntypedActor;
import com.ing.ica.alpakkakafkademo.service.MinerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Slf4j
@Named("miner")
@Scope("prototype")
public class Miner extends UntypedActor {

    public enum Message {
        OBTAIN_MATERIALS
    }

    private final MinerService minerService;

    @Inject
    public Miner(MinerService service) {
        this.minerService = service;
    }


    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Miner] message received: \"{}\".", o);

        if (o == Message.OBTAIN_MATERIALS) {
            log.info("[Miner] mining ...");
            minerService.obtenerMinerales();
            log.info("[Miner] mining done.");
            getSender().tell(Blacksmith.Mensaje.MATERIALS_OBTAINED, getSelf());
        } else {
            unhandled(o);
        }
    }
}
