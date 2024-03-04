package com.discord.bot.game;
import com.discord.bot.Inventary.Inventary;
import com.discord.bot.Objects.ObjectsList;
import com.discord.bot.user.User;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import com.discord.bot.Niveles.Nivel1;
import com.discord.bot.Objects.ObjectsListService;
import com.discord.bot.Objects.ObjectsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class GeneralGame {
    ArrayList<Inventary> objects = new ArrayList<>(5);
    User user = new User();
    @Autowired
    ObjectsListRepository repositoryObjects;
    public void Init()
    {
        Nivel1 n1 = new Nivel1();
        final String token = "token";
        final GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    String botName = event.getSelf().getUsername();
                    System.out.println("Conectado como: " + botName);
                });
        client.onDisconnect().block();

        System.out.println("Bienvenido a PandWar, este es un juego de texto con dos niveles\n" +
                            "a medida que avanzas encontraras cofres, los cuales podras recojer\n" +
                            "objetos y guardarlos en tu inventario, el cual tiene una capacidad maxima de 5 objetos.\n" +
                            "Para pasar de nivel deberas luchar contra enemigos y al final luchar\n" +
                            "contra un bos final.");
        n1.level1();
    }
}
