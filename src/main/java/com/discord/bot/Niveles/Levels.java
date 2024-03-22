package com.discord.bot.Niveles;

import jakarta.persistence.*;

@Entity
@Table(name = "Levels")
public class Levels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcio")
    String descripcio;

    @Column(name = "Enemys")
    int enemys;

    @Column(name = "cofre")
    int cofres;

    public Levels(){
    }

    public Levels(String descripcio, int enemys, int cofres){
        this.descripcio = descripcio;
        this.enemys = enemys;
        this.cofres = cofres;
    }

}
