package com.discord.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.discord.bot.game.GeneralGame;

@SpringBootApplication
public class BotApplication {

	static GeneralGame gg = new GeneralGame();
	public static void main(String[] args) {

		SpringApplication.run(BotApplication.class, args);
		gg.Init();
	}
}
