package com.discord.bot.Player;

import com.discord.bot.Enemy.Enemy;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import jakarta.persistence.Transient;
import com.discord.bot.Player.Player;
import java.util.Random;

public class PlayerActions {

    @Transient
    private final GatewayDiscordClient client;
    public PlayerActions(GatewayDiscordClient client){
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

    public void perderVidasPlayer(MessageCreateEvent event){
        Player player = new Player();
        event.getMessage().getChannel().block().createMessage("El Player pierde una vida te quedan = "+player.vidas).block();
        player.vidas--;
    }

    public void DiePlayer()
    {
        Player player = new Player();
        if(player.vidas == 0){
            System.out.println("El juagdor ha muerto");
        }
    }
}
