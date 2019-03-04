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

    public static ActorRef swordman;
    public static ActorRef blacksmith;
    public static ActorRef miner;

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(AlpakkaKafkaDemoApplication.class, args);
        ActorSystem actorSystem = applicationContext.getBean(ActorSystem.class);

        swordman = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("swordman"), "swordman");
        blacksmith = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("blacksmith"), "blacksmith");
        miner = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("miner"), "miner");

        swordman.tell(Swordman.Mensaje.ESPADA_ROTA, ActorRef.noSender());
    }

}
