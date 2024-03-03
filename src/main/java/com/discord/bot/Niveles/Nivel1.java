package com.discord.bot.Niveles;
import com.discord.bot.Inventary.ItemRepository;
import com.discord.bot.Player.Player;
import com.discord.bot.Enemy.Enemy;
import com.discord.bot.user.User;
import com.discord.bot.Inventary.Inventary;
import com.discord.bot.Inventary.InventoryServices;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;
public class Nivel1 {
    static Scanner scan = new Scanner(System.in);
    Player player = new Player();
    Enemy enemy = new Enemy();
    User user = new User();
    Inventary inv = new Inventary();
    ItemRepository ir;

    boolean enemigoVivo = true;
    InventoryServices is = new InventoryServices(ir);

    public void level1(){
        System.out.println("Bienvenido al nivel 1");
        System.out.println("Al entrat encuentras un cofre, lo quieres abrir? (S/N)");
        String abrir = scan.nextLine();
        if(abrir.equals("S")){
            System.out.println("Encontraste: \n" +
                                "Elixir de la Vida\n" +
                                "Manzana podrida\n" +
                                "Espada desafilada\n" +
                                "Quieres agregar alguno de estos objetos? (S/N)");
            String agregar = scan.nextLine();
            if(agregar.equals("S")){

            }
        }
        System.out.println("Al entrar te has encontrado con un enemigo, que haras, lucharas o huiras? (L / H)");
        String combateChoice = scan.nextLine();

        if(combateChoice.equals("H")){
            player.perderVidasPlayer();
            System.out.println("El jugador pierde una vida al intentar escapar "+player.vidas);
        } else if(combateChoice.equals("L")){
            System.out.println("Has escogido luchar contra el enemigo. Puedes hacer varias cosas primero\n" +
                                "Escoje: ");
            System.out.println("Atacar al Enemigo -- 1\n" +
                                "Ver el inventario -- 2");
            int combateAccion = scan.nextInt();
            switch(combateAccion){
                case 1:
                    while(enemigoVivo){
                        player.ataquePlayer();
                        if(enemy.vidas > 1){
                            enemy.ataqueEnemy();
                            if(player.vidas > 1){
                                player.ataquePlayer();
                                if(enemy.vidas <= 0){
                                    enemy.dead();
                                    enemigoVivo = false;
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    is.obtenerInventario();
                    System.out.println("Quieres utilizar algun objeto? (S/N)");
                    String objeto = scan.nextLine();
                    if(objeto.equals("S")){
                        is.utilizarItem();
                    }
                    break;
            }
        }
    }
}
