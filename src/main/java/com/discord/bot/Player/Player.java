package com.discord.bot.Player;

import java.io.Serializable;

import discord4j.core.GatewayDiscordClient;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vidas")
    public int vidas;

    @Column(name = "name")
    public String name;

    @Column(name = "level")
    public int level;
    public Player(){
    }

    public Player(String name, int vidas, int level){
        this.name = name;
        this.vidas = vidas;
        this.level = level;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}

