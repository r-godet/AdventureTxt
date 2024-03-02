package com.discord.bot.Enemy;

import java.util.Random;

public class FinalEnemy {

    String name;
    int vidas = 3;

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
