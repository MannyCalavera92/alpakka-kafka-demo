package com.ing.ica.alpakkakafkademo.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.ing.ica.alpakkakafkademo.AlpakkaKafkaDemoApplication;
import com.ing.ica.alpakkakafkademo.service.BlacksmithService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Slf4j
@Named("blacksmith")
@Scope("prototype")
public class Blacksmith extends UntypedActor {
    public enum Mensaje {
        CRAFT_SWORD,
        MATERIALS_OBTAINED
    }

    private ArrayList<ActorRef> swordmen;
    private final BlacksmithService blacksmithService;

    @Inject
    public Blacksmith(BlacksmithService blacksmithService) {
        this.blacksmithService = blacksmithService;
    }

    @Override
    public void preStart() {
        swordmen = new ArrayList<>();
    }

    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Blacksmith] message received: \"{}\".", o);

        if (o == Mensaje.CRAFT_SWORD) {
            swordmen.add(getSender());
            AlpakkaKafkaDemoApplication.miner.tell(Miner.Message.OBTAIN_MATERIALS, getSelf());
        } else if (o == Mensaje.MATERIALS_OBTAINED) {
            log.info("[Blacksmith] forging sword ...");
            blacksmithService.crearEspada();
            log.info("[Blacksmith] sword forged.");
            if (!swordmen.isEmpty()) {
                swordmen.get(0).tell(Swordman.Menssage.NEW_SWORD, getSelf());
                swordmen.remove(0);
            }
        } else {
            unhandled(o);
        }
    }
}