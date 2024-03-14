package com.discord.bot.Niveles;
import com.discord.bot.Inventary.ItemRepository;
import com.discord.bot.Objects.ObjectsList;
import com.discord.bot.Objects.ObjectsListRepository;
import com.discord.bot.Player.Player;
import com.discord.bot.Enemy.Enemy;
import com.discord.bot.user.User;
import com.discord.bot.Inventary.Inventary;
import com.discord.bot.Inventary.InventoryServices;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.spec.legacy.LegacyMessageCreateSpec;
import org.springframework.beans.factory.annotation.Autowired;
import com.discord.bot.game.GeneralGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;

public class Nivel1 {

    @Autowired
    ItemRepository repositoryInventary;

    private final GatewayDiscordClient client;

    @Autowired
    private GeneralGame gg;
    static Scanner scan = new Scanner(System.in);
    User user = new User();
    Inventary inv = new Inventary();
    @Autowired
    ObjectsListRepository repositoryObjects;
    @Autowired
    private InventoryServices is;
    private String estadoActual;

    public Nivel1(GatewayDiscordClient client, ObjectsListRepository repositoryObjects, InventoryServices is) {
        this.client = client;
        this.repositoryObjects = repositoryObjects;
        this.is = is;
        this.estadoActual = "inicio";
        inicializarManejadorDeEventos();
    }
    public void inicializarManejadorDeEventos() {
        inicializarObjetosBase();
        Player player = new Player(client);
        Enemy enemy = new Enemy(client);
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    String content = event.getMessage().getContent();
                    switch (estadoActual) {
                        case "inicio":
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
                                player.ataquePlayer(event);
                                estadoActual = "esperandoAccion";
                            } else if (content.equals("Abrir_inventario")) {
                                abrirInventario(event);
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
        List<ObjectsList> objetosIniciales = new ArrayList<>();
        objetosIniciales.add(new ObjectsList("Elixir de la vida"));
        objetosIniciales.add(new ObjectsList("Manzana Podrida"));
        objetosIniciales.add(new ObjectsList("Espada desafilada"));
        objetosIniciales.add(new ObjectsList("Espada afilada"));
        objetosIniciales.add(new ObjectsList("Elixir de la resureccion"));
        objetosIniciales.add(new ObjectsList("Portal de Huida"));

        objetosIniciales.forEach(objeto -> repositoryObjects.save(objeto));
        System.out.println("Objetos base inicializados y guardados en la base de datos.");
    }

    public void guardarObjetos(String nombreObjeto){

        Inventary newItem = new Inventary(nombreObjeto, 1);
        repositoryInventary.save(newItem);

        System.out.println("item guardado con exito en el inventario." +nombreObjeto);
    }
}

