package com.ing.ica.alpakkakafkademo.actors;

import akka.actor.UntypedActor;
import com.ing.ica.alpakkakafkademo.AlpakkaKafkaDemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Slf4j
@Named("swordman")
@Scope("prototype")
public class Swordman extends UntypedActor {


    public enum Menssage {
        NEW_SWORD,
        BROKEN_SWORD
    }

    @Override
    public void onReceive(Object o) {
        log.info("[Swordman] message received: \"{}\".", o);

        if (o == Menssage.BROKEN_SWORD) {
            AlpakkaKafkaDemoApplication.blacksmith.tell(Blacksmith.Mensaje.CRAFT_SWORD, getSelf());
        } else if (o == Menssage.NEW_SWORD) {
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
