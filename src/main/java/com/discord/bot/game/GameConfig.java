package com.discord.bot.game;

import com.discord.bot.Objects.ObjectsListRepository;
import discord4j.core.GatewayDiscordClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    @Bean
    public GeneralGame generalGame(GatewayDiscordClient client, ObjectsListRepository objectsListRepository) {
        return new GeneralGame(client,objectsListRepository);
    }
}
