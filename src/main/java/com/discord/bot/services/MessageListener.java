package com.discord.bot.services;

import com.discord.bot.config.CommandHandler;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;


public abstract class MessageListener {

    private final CommandHandler commandMap;

    @Autowired
    protected MessageListener(CommandHandler commandHandler) {
        this.commandMap = commandHandler;
    }
    public Mono<Void> processCommand(Message eventMessage) {

        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> commandMap.getCommandMap().containsKey(message.getContent().toLowerCase()))
                .flatMap(Message::getChannel)
                .flatMap(channel -> {
                    String command = eventMessage.getContent().toLowerCase();
                    return commandMap.getCommandMap().get(command).apply(eventMessage).then();
                });
    }
}