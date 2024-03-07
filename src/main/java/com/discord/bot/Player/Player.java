package com.discord.bot.Player;
import com.discord.bot.services.MessageCreateListener;
import com.discord.bot.user.User;

import javax.management.Query;
import java.io.Serializable;
import java.util.Random;
import com.discord.bot.Enemy.Enemy;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    private Long id;

    @Column(name = "vidas")
    public int vidas;

    public void ataquePlayer(MessageCreateListener event){
        Random random = new Random();

        int randomNum = random.nextInt(2) + 1;
        if(randomNum == 1){
            System.out.println("el ataque ha sido efectivo");
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
