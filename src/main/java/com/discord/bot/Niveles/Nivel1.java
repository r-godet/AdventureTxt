package com.discord.bot.Niveles;
import com.discord.bot.Inventary.ItemRepository;
import com.discord.bot.Objects.ObjectsList;
import com.discord.bot.Objects.ObjectsListRepository;
import com.discord.bot.Player.Player;
import com.discord.bot.Enemy.Enemy;
import com.discord.bot.Player.PlayerActions;
import com.discord.bot.user.User;
import com.discord.bot.Inventary.Inventary;
import com.discord.bot.Inventary.InventoryServices;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.spec.legacy.LegacyMessageCreateSpec;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;

@Component
public class Nivel1 {
    User user = new User();
    Inventary inv = new Inventary();
    Levels nivel1 = new Levels("nivel1", 2, 1);
    public String estadoActual;
    public int ataque = 1;

    private final GatewayDiscordClient client;
    @Autowired
    private ItemRepository repositoryInventary;
    @Autowired
    private final ObjectsListRepository repositoryObjects;
    @Autowired
    private final InventoryServices is;

    @Autowired
    private final LevelsRepository lR;

    public Nivel1(GatewayDiscordClient client, ObjectsListRepository repositoryObjects, InventoryServices is, LevelsRepository lR) {
        if(lR.count() == 0){
            lR.save(nivel1);
            System.out.println("Nivel Guardado");
        } else{
            System.out.println("El nivel ya estaba guardado");
        }
        this.client = client;
        this.repositoryObjects = repositoryObjects;
        this.is = is;
        this.lR = lR;
        this.estadoActual = "inicio";
        inicializarManejadorDeEventos();
    }
    public void inicializarManejadorDeEventos() {
        System.out.println("Iniciador de Eventos inciado");
        inicializarObjetosBase();
        PlayerActions player = new PlayerActions(client);
        Enemy enemy = new Enemy(client);

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    String content = event.getMessage().getContent();

                    switch (estadoActual) {
                        case "inicio":
                            System.out.println("iniciado correctamente switch");
                            if (content.startsWith("S")) {
                                event.getMessage().getChannel().block().createMessage("Bienvenido al nivel1\n" +
                                        "Al entrar te has encontrado un enemigo, entras en combate con este pero antes\n" +
                                        "puedes hacer varias cosas: \n" +
                                        "Atacar\n" +
                                        "Abrir_inventario\n" +
                                        "Huir").block();
                                estadoActual = "esperandoAccion";
                            }
                            break;
                        case "esperandoAccion":
                            if (content.equals("Atacar")) {
                                atacar(event);
                                if(ataque == 1) {
                                    player.ataquePlayer(event);
                                    ataque = 2;
                                }
                                else if(ataque == 2){
                                    enemy.ataqueEnemy(event);
                                }

                                estadoActual = "esperandoAccion";
                            } else if (content.equals("Abrir_inventario")) {
                                abrirInventario(event);
                                estadoActual = "esperandoAccion";
                            }
                            else if(content.equals("Huir")){

                                estadoActual = "esperandoAccion";
                            }
                            break;
                    }
                });
    }


    public void atacar(MessageCreateEvent event){
        Objects.requireNonNull(event.getMessage().getChannel().block().createMessage("Has escogido atacar").block());
    }

    public void abrirInventario(MessageCreateEvent event){
        Objects.requireNonNull(event.getMessage().getChannel().block().createMessage(is.abrirInventario(event)).block());
    }

    public void inicializarObjetosBase() {
        System.out.println("iniciando objetos");
        if(repositoryObjects.count() == 0){
            System.out.println("objetos instanciandose...");
            List<ObjectsList> objetosIniciales = new ArrayList<>();
            objetosIniciales.add(new ObjectsList("Elixir de la vida"));
            objetosIniciales.add(new ObjectsList("Manzana Podrida"));
            objetosIniciales.add(new ObjectsList("Espada desafilada"));
            objetosIniciales.add(new ObjectsList("Espada afilada"));
            objetosIniciales.add(new ObjectsList("Elixir de la resureccion"));
            objetosIniciales.add(new ObjectsList("Portal de Huida"));

            objetosIniciales.forEach(repositoryObjects::save);
            System.out.println("Objetos base inicializados y guardados en la base de datos.");
        }
        else
        {
            System.out.println("Objetos ya inyectado en la base de datos.");
        }
    }

    public void guardarObjetos(String nombreObjeto, MessageCreateEvent event){
        Inventary newItem = new Inventary(nombreObjeto, 1);
        repositoryInventary.save(newItem);
        Objects.requireNonNull(event.getMessage().getChannel().block().createMessage("item guardado con exito en el inventario. " + nombreObjeto).block());
    }
}

