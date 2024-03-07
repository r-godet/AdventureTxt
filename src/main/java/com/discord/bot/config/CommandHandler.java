package com.discord.bot.config;

import com.discord.bot.Niveles.Nivel1;
import com.discord.bot.game.GeneralGame;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class CommandHandler {
    private final Map<String, Function<Message, Mono<Void>>> commandMap;
    private final GeneralGame game;
    private final Nivel1 n1;

    @Autowired
    public CommandHandler(GeneralGame game, Nivel1 n1) {
        this.game = game;
        this.n1 = n1;
        this.commandMap = new HashMap<>();

        initializeCommands();
    }

    private void initializeCommands() {
        commandMap.put("!todo", this::handleTodoCommand);
        commandMap.put("!ping", this::handlePingCommand);
        commandMap.put("start", this::gameStart);
        // Pots afegir més comandes aquí
    }

    public Map<String, Function<Message, Mono<Void>>> getCommandMap() {
        return commandMap;
    }

    private Mono<Void> handleTodoCommand(Message message) {
        return message.getChannel()
                .flatMap(channel -> channel.createMessage("Things to do today:\n - write a bot\n - eat lunch\n - play a game")).then();
    }

    private Mono<Void> gameStart(Message message) {
        return message.getChannel()
                .flatMap(channel -> channel.createMessage(game.Start())).then();
    }


    private Mono<Void> handlePingCommand(Message message) {
        return message.getChannel()
                .flatMap(channel -> channel.createMessage("Pong!")).then();
    }
    // Afegir més mètodes privats per a altres comandes si és necessari
}