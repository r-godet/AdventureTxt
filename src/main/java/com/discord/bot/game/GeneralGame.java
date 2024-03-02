package com.discord.bot.game;
import com.discord.bot.user.User;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import com.discord.bot.Niveles.Nivel1;

public class GeneralGame {
    User user = new User();

    public void Init()
    {
        Nivel1 n1 = new Nivel1();
        final String token = "tu_token_aquí";
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
                            "objetos y guardarlos en tu inventario, el cual solo caben 5 objetos\n" +
                            "para pasar de nivel deberas luchar contra enemigos y al final luchar\n" +
                            "contra un bos final.");


        n1.level1();
    }

}
