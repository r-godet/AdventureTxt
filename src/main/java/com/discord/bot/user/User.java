package com.discord.bot.user;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "level")
    private String level;


    public User() {
        super();
    }

    public User(Long id, String name, String username, String level) {
        super();
        this.Id = id;
        this.name = name;
        this.username = username;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLevel(String level){ this.level = level; }

    public String getLevel() { return level; }

}
