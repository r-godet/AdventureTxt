package com.discord.bot.Player;
import com.discord.bot.services.MessageCreateListener;
import com.discord.bot.user.User;

import javax.management.Query;
import java.io.Serializable;
import java.util.Random;
import com.discord.bot.Enemy.Enemy;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    private Long id;

    @Column(name = "vidas")
    public int vidas;

    @Transient
    private final GatewayDiscordClient client;
    public Player(GatewayDiscordClient client){
        this.client = client;
    }

    public void ataquePlayer(MessageCreateEvent event) {
        Enemy enemy = new Enemy(client);
        Random random = new Random();
        int randomNum = random.nextInt(2) + 1;
        int randomNum2 = random.nextInt(3) + 4;
        if(randomNum == 1 | randomNum2 == 4){
            event.getMessage().getChannel().block().createMessage("Ataque Completado con éxito").block();
            enemy.perderVidasEnemys(event);
        } else {
            event.getMessage().getChannel().block().createMessage("Ataque no completado.").block();
        }
    }

    public void perderVidasPlayer(){

        vidas--;
    }

    public void DiePlayer()
    {
        if(vidas == 0){
            System.out.println("El juagdor ha muerto");
        }
    }

}

