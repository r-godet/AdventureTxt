package com.discord.bot.services;

import com.discord.bot.config.CommandHandler;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;


public abstract class MessageListener {

    private final CommandHandler commandMap = new CommandHandler();
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