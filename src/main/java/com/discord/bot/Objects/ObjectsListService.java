package com.discord.bot.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectsListService {

    private final ObjectsListRepository repository;

    @Autowired
    public ObjectsListService(ObjectsListRepository repository) {
        this.repository = repository;
    }

    public void guardarObjetosList(List<ObjectsList> objetos) {
        repository.saveAll(objetos);
    }
}