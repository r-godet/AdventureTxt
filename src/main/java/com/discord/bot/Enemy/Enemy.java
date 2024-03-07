package com.discord.bot.Enemy;

import java.io.Serializable;
import java.util.Random;
import com.discord.bot.Player.Player;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "enemy")
public class Enemy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    public int vidas = 2;
    Player player = new Player();

    public void perderVidasEnemys() {
        vidas--;
    }

    public void ataqueEnemy()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(2) + 1;

        if(randomNumber == 1){
            player.perderVidasPlayer();
        }
    }
    public void dead(){
        if(vidas == 0){
            System.out.println("El enemigo ha muerto, continua tu camino...");
        }
    }
}
