package com.ing.ica.alpakkakafkademo.actors;

import akka.actor.UntypedActor;
import com.ing.ica.alpakkakafkademo.AlpakkaKafkaDemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("espadachin")
@Scope("prototype")
public class Swordman extends UntypedActor {


    public enum Mensaje {
        ESPADA_NUEVA,
        ESPADA_ROTA
    }

    private static final Logger log = LoggerFactory.getLogger(Swordman.class);

    @Override
    public void onReceive(Object o) {
        log.info("[Swordman] message received: \"{}\".", o);

        if (o == Mensaje.ESPADA_ROTA) {
            AlpakkaKafkaDemoApplication.blacksmith.tell(Blacksmith.Mensaje.CREAR_ESPADA, getSelf());
        } else if (o == Mensaje.ESPADA_NUEVA) {
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
