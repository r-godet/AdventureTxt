package com.discord.bot.Inventary;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inventary")
public class Inventary implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "object")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    public Inventary(){
    }

    public Inventary(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void showInventario()
    {

    }
}
