package com.discord.bot.Enemy;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import com.discord.bot.Player.Player;
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

    public void ataqueEnemy()
    {
        Player player = new Player(client);
        Random random = new Random();
        int randomNumber = random.nextInt(2) + 1;

        if(randomNumber == 1){
            player.perderVidasPlayer();
        }
    }
    public void dead(){
        if(vidas == 0){
            System.out.println("El enemigo ha muerto, continua tu camino...");
        }
    }
}
