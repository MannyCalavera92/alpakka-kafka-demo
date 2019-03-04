package com.ing.ica.alpakkakafkademo.actors;

import akka.actor.UntypedActor;
import com.ing.ica.alpakkakafkademo.service.MineroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named("minero")
@Scope("prototype")
public class Miner extends UntypedActor {

    public enum Mensaje {
        OBTENER_MATERIALES
    }

    private static final Logger log = LoggerFactory.getLogger(Miner.class);
    private final MineroService mineroService;

    @Inject
    public Miner(MineroService service) {
        this.mineroService = service;
    }


    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Miner] message received: \"{}\".", o);

        if (o == Mensaje.OBTENER_MATERIALES) {
            log.info("[Miner] mining ...");
            mineroService.obtenerMinerales();
            log.info("[Miner] mining done.");
            getSender().tell(Blacksmith.Mensaje.MATERIALES, getSelf());
        } else {
            unhandled(o);
        }
    }
}
