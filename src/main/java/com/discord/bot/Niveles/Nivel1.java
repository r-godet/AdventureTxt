package com.discord.bot.Niveles;
import com.discord.bot.Inventary.ItemRepository;
import com.discord.bot.Objects.ObjectsList;
import com.discord.bot.Objects.ObjectsListRepository;
import com.discord.bot.Player.Player;
import com.discord.bot.Enemy.Enemy;
import com.discord.bot.user.User;
import com.discord.bot.Inventary.Inventary;
import com.discord.bot.Inventary.ItemRepository;
import com.discord.bot.Inventary.InventoryServices;
import jdk.swing.interop.SwingInterOpUtils;
import com.discord.bot.Inventary.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import com.discord.bot.game.GeneralGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Nivel1 {
    @Autowired
    ItemRepository repositoryInventary;

    GeneralGame gg = new GeneralGame();
    static Scanner scan = new Scanner(System.in);
    Player player = new Player();
    Enemy enemy = new Enemy();
    User user = new User();
    Inventary inv = new Inventary();
    @Autowired
    ObjectsListRepository repositoryObjects;



    boolean enemigoVivo = true;
    @Autowired
    InventoryServices is;

    public void level1() {
        inicializarObjetosBase();
        System.out.println("Bienvenido al nivel 1");
        System.out.print("Al entrat encuentras un cofre, lo quieres abrir? (S/N): ");
        String abrir = scan.nextLine();
        if (abrir.equals("S")) {
            System.out.print("En el cofre has encontrado: \n" +
                    "1. Elixir de vida\n" +
                    "2. Dos manzanas podridas\n" +
                    "3. Espada desafilada\n" +
                    "Quieres coger alguno de estos objetos? (S/N): ");
            String agregar = scan.nextLine();
            if (agregar.equals("S")) {
                System.out.print("Que objecto quieres obtener? (introduce el numero en el que esta ordenado): ");
                int agregarObj = scan.nextInt();
                switch (agregarObj) {
                    case 1:
                        guardarObjetos("Elixir de la Vida");
                        System.out.println("\nHas obtenido el elexir de vida");
                        break;
                    case 2:
                        guardarObjetos("Manzanas Podridas");
                        System.out.println("\nHas obtenido dos manzanas podridas");
                        break;
                    case 3:
                        guardarObjetos("Espada desafilada");
                        System.out.println("\nHas obtenido la espada desafilada");
                        break;
                    default:
                        System.out.println("\nOpción inválida.\n");
                        return;
                }
            }
        }
        System.out.print("Continuando con tu camino te has encontrado con un enemigo. Vas a querer luchar o huir? (L/H): ");
        String combateChoice = scan.nextLine();

        if (combateChoice.equals("H")) {
            player.perderVidasPlayer();
            System.out.println("El jugador pierde una vida al intentar escapar " + player.vidas);
        } else if (combateChoice.equals("L")) {
            System.out.println("Has escogido luchar contra el enemigo. Puedes hacer varias cosas primero\n" +
                    "Escoje: ");
            System.out.println("Atacar al Enemigo -- 1\n" +
                    "Ver el inventario -- 2");
            int combateAccion = scan.nextInt();
            switch (combateAccion) {
                case 1:
                    while (enemigoVivo) {
                        player.ataquePlayer();
                        if (enemy.vidas > 1) {
                            enemy.ataqueEnemy();
                            if (player.vidas > 1) {
                                player.ataquePlayer();
                                if (enemy.vidas <= 0) {
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
                    if (objeto.equals("S")) {
                        is.utilizarItem();
                    }
                    break;
            }
        }
    }

    public void inicializarObjetosBase() {
        List<ObjectsList> objetosIniciales = new ArrayList<>();
        objetosIniciales.add(new ObjectsList("Elixir de la vida"));
        objetosIniciales.add(new ObjectsList("Manzana Podrida"));
        objetosIniciales.add(new ObjectsList("Espada desafilada"));
        objetosIniciales.add(new ObjectsList("Espada afilada"));
        objetosIniciales.add(new ObjectsList("Elixir de la resureccion"));
        objetosIniciales.add(new ObjectsList("Portal de Huida"));

        objetosIniciales.forEach(objeto -> repositoryObjects.save(objeto));
        System.out.println("Objetos base inicializados y guardados en la base de datos.");
    }

    public void guardarObjetos(String nombreObjeto){

        Inventary newItem = new Inventary(nombreObjeto, 1);
        repositoryInventary.save(newItem);

        System.out.println("item guardado con exito en el inventario." +nombreObjeto);
    }
}

