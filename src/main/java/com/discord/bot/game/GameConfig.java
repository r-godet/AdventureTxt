package com.discord.bot.game;

import com.discord.bot.Inventary.InventoryServices;
import com.discord.bot.Niveles.LevelsRepository;
import com.discord.bot.Objects.ObjectsListRepository;
import com.discord.bot.Player.PlayerRepository;
import discord4j.core.GatewayDiscordClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    @Bean
    public GeneralGame generalGame(GatewayDiscordClient client, ObjectsListRepository objectsListRepository, InventoryServices is, LevelsRepository lr, PlayerRepository pr) {
        return new GeneralGame(client,objectsListRepository, is, lr, pr);
    }
}
