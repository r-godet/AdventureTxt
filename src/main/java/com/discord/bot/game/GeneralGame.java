package com.discord.bot.game;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.discord.bot.Inventary.Inventary;
import com.discord.bot.Inventary.InventoryServices;
import com.discord.bot.Niveles.LevelsRepository;
import com.discord.bot.Objects.ObjectsList;
import com.discord.bot.Player.Player;
import com.discord.bot.Player.PlayerRepository;
import com.discord.bot.user.User;
import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import com.discord.bot.Niveles.Nivel1;
import com.discord.bot.Objects.ObjectsListService;
import com.discord.bot.Objects.ObjectsListRepository;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.discord.bot.config.BotConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import discord4j.core.object.entity.channel.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class GeneralGame {

    private final GatewayDiscordClient client;
    ArrayList<Inventary> objects = new ArrayList<>(5);
    User user = new User();
    public String name;

    Player player = new Player(name, 3, 1);
    @Autowired
    ObjectsListRepository repositoryObjects;

    @Autowired
    InventoryServices is;

    @Autowired
    LevelsRepository lr;

    @Autowired
    PlayerRepository pr;
    @Autowired
    public GeneralGame(GatewayDiscordClient client, ObjectsListRepository repositoryObjects, InventoryServices is, LevelsRepository lr, PlayerRepository pr) {
        this.client = client;
        this.repositoryObjects = repositoryObjects;
        this.is = is;
        this.lr = lr;
        this.pr = pr;
        Start();
    }
    public String Start(){
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    String content = event.getMessage().getContent();
                    if (content.startsWith("start")) {
                        name = event.getMessage().getAuthor().get().getUsername();
                        pr.save(player);
                        event.getMessage().getChannel().block().createMessage("Iniciada sesion como: "+name).block();
                        Objects.requireNonNull(event.getMessage().getChannel().block()).createMessage("Bienvenido a PandWar, este es un juego de texto con dos niveles\n" +
                                "a medida que avanzas encontraras cofres, los cuales podras recojer\n" +
                                "objetos y guardarlos en tu inventario, el cual tiene una capacidad maxima de 5 objetos.\n" +
                                "Para pasar de nivel deberas luchar contra enemigos y al final luchar\n" +
                                "contra un bos final.\n" +
                                "Ahora entraras en el nivel 1... Estas listo? (S/N)").block();
                    }
                });
        Nivel1 n1 = new Nivel1(client, repositoryObjects, is, lr);
        client.onDisconnect().block();
        return "start";
    }
}
