package com.discord.bot.Enemy;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import com.discord.bot.Player.Player;
import com.discord.bot.Player.PlayerActions;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "enemy")
public class Enemy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    public int vidas = 2;
    @Transient
    private final GatewayDiscordClient client;

    public Enemy(GatewayDiscordClient client) {
        this.client = client;
    }

    public void perderVidasEnemys(MessageCreateEvent event) {
        Objects.requireNonNull(event.getMessage().getChannel().block().createMessage("El enemigo pierde 1 vida").block());
        vidas--;
    }

    public void ataqueEnemy(MessageCreateEvent event) {
        Random random = new Random();
        PlayerActions player = new PlayerActions(client);
        int randomNum = random.nextInt(2) + 1;
        int randomNum2 = random.nextInt(3) + 4;
        if(randomNum == 1 | randomNum2 == 4){
            event.getMessage().getChannel().block().createMessage("Ataque Completado con éxito").block();
            player.perderVidasPlayer(event);
        } else {
            event.getMessage().getChannel().block().createMessage("Ataque no completado.").block();
        }
    }
    public void dead(){
        if(vidas == 0){
            System.out.println("El enemigo ha muerto, continua tu camino...");
        }
    }
}
