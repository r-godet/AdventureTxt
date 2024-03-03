package com.discord.bot.Objects;
import com.discord.bot.Objects.ObjectsList;
import com.discord.bot.Objects.ObjectsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ObjectsListController {

    private final ObjectsListService service;

    @Autowired
    public ObjectsListController(ObjectsListService service) {
        this.service = service;
    }

    @PostMapping("/objetos")
    public void guardarObjetos(@RequestBody List<ObjectsList> objetos) {
        service.guardarObjetosList(objetos);
    }
}
