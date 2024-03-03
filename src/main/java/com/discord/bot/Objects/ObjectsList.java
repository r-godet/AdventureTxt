package com.discord.bot.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "Objects")
public class ObjectsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Objeto")
    private String objeto;

    public ObjectsList() {
    }
    public ObjectsList(String objeto) {
        this.objeto = objeto;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }
}
