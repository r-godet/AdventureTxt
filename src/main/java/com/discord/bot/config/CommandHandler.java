package com.discord.bot.config;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandHandler {
    private final Map<String, Function<Message, Mono<Void>>> commandMap;

    public CommandHandler() {
        commandMap = new HashMap<>();
        commandMap.put("!todo", this::handleTodoCommand);
        commandMap.put("!ping", this::handlePingCommand);
        // Pots afegir més comandes aquí
    }

    public Map<String, Function<Message, Mono<Void>>> getCommandMap() {
        return commandMap;
    }

    public Mono<Void> handleTodoCommand(Message message) {

        return message.getChannel()
                .flatMap(channel -> channel.createMessage("Things to do today:\n - write a bot\n - eat lunch\n - play a game")).then();
    }

    public Mono<Void> handlePingCommand(Message message) {
        return message.getChannel()
                .flatMap(channel -> channel.createMessage("Pong!")).then();
    }
    // Afegir més mètodes privats per a altres comandes si és necessari
}
