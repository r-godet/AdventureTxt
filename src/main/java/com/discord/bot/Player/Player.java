package com.discord.bot.Player;
import com.discord.bot.user.User;

import javax.management.Query;
import java.util.Random;
import com.discord.bot.Enemy.Enemy;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Entity
@Table(name = "player")
public class Player {

    @Column(name = "vidas")
    public int vidas = 3;
    Enemy enemy = new Enemy();

    public void ataquePlayer(){
        Random random = new Random();

        int randomNum = random.nextInt(2) + 1;
        if(randomNum == 1){
            System.out.println("el ataque ha sido efectivo");
            enemy.perderVidasEnemys();
        }
        else {
            System.out.println("El ataque no ha sido efectivo");
        }
    }

    public void perderVidasPlayer(){
        vidas--;
    }

    public void DiePlayer(){
        if(vidas == 0){
            System.out.println("El juagdor ha muerto");
        }
    }

}
