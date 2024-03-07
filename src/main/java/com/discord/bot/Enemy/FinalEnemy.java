package com.discord.bot.Enemy;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Random;

@Entity
@Table(name = "inventary")
public class FinalEnemy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    public int vidas = 3;

    public void perderVidasEnemys(int vidas_enemys) {
        vidas_enemys--;
    }
    public void ataqueEnemy()
    {
        System.out.println("Enemigo va a atacar.");
        Random random = new Random();
        int randomNumber = random.nextInt(2) + 1;

        if(randomNumber == 1){
            System.out.println("El ataque ha sido efectivo");
        }
        else{
            System.out.println("El ataque no ha sido efectivo");
        }
    }
    public void dead(){
        if(vidas == 0){
            System.out.println("El enemigo final ha muerto");
        }
    }

}
