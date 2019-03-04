package com.ing.ica.alpakkakafkademo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.ing.ica.alpakkakafkademo.actors.Swordman;
import com.ing.ica.alpakkakafkademo.main.SpringExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AlpakkaKafkaDemoApplication {

    public static ActorRef espadachin;
    public static ActorRef herrero;
    public static ActorRef minero;

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(AlpakkaKafkaDemoApplication.class, args);
        ActorSystem actorSystem = applicationContext.getBean(ActorSystem.class);

        espadachin = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("espadachin"), "espadachin");
        herrero = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("herrero"), "herrero");
        minero = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("minero"), "minero");

        espadachin.tell(Swordman.Mensaje.ESPADA_ROTA, ActorRef.noSender());
    }

}
