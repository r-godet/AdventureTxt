package com.discord.bot.Enemy;

import java.util.Random;
import com.discord.bot.Player.Player;

public class Enemy {

    String name;
    public int vidas = 2;
    Player player = new Player();

    public void perderVidasEnemys() {
        vidas--;
    }

    public void ataqueEnemy()
    {
        System.out.println("Enemigo va a atacar.");
        Random random = new Random();
        int randomNumber = random.nextInt(2) + 1;

        if(randomNumber == 1){
            System.out.println("El ataque ha sido efectivo");
            player.perderVidasPlayer();
        }
        else{
            System.out.println("El ataque no ha sido efectivo");
        }
    }
    public void dead(){
        if(vidas == 0){
            System.out.println("El enemigo ha muerto, continua tu camino...");
        }
    }
}
