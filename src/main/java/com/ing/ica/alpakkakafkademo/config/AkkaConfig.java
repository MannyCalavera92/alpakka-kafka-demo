package com.ing.ica.alpakkakafkademo.config;

import akka.actor.ActorSystem;
import com.ing.ica.alpakkakafkademo.main.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("ActorSystem");
        SpringExtension.SpringExtProvider.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }
}