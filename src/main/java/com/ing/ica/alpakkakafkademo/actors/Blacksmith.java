package com.ing.ica.alpakkakafkademo.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.ing.ica.alpakkakafkademo.AlpakkaKafkaDemoApplication;
import com.ing.ica.alpakkakafkademo.service.HerreroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named("herrero")
@Scope("prototype")
public class Blacksmith extends UntypedActor {
    public enum Mensaje {
        CREAR_ESPADA,
        MATERIALES
    }

    private static final Logger log = LoggerFactory.getLogger(Blacksmith.class);
    private ArrayList<ActorRef> espadachines;
    private final HerreroService herreroService;

    @Inject
    public Blacksmith(HerreroService herreroService) {
        this.herreroService = herreroService;
    }

    @Override
    public void preStart() {
        espadachines = new ArrayList<>();
    }

    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Blacksmith] message received: \"{}\".", o);

        if (o == Mensaje.CREAR_ESPADA) {
            espadachines.add(getSender());
            AlpakkaKafkaDemoApplication.miner.tell(Miner.Mensaje.OBTENER_MATERIALES, getSelf());
        } else if (o == Mensaje.MATERIALES) {
            log.info("[Blacksmith] forging sword ...");
            herreroService.crearEspada();
            log.info("[Blacksmith] sword forged.");
            if (!espadachines.isEmpty()) {
                espadachines.get(0).tell(Swordman.Mensaje.ESPADA_NUEVA, getSelf());
                espadachines.remove(0);
            }
        } else {
            unhandled(o);
        }
    }
}