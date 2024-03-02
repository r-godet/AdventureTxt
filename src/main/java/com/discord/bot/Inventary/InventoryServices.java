package com.discord.bot.Inventary;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServices {

    private final ItemRepository itemRepository;

    @Autowired
    public InventoryServices(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void agregarItem(String nombre) {

    }

    public List<AbstractReadWriteAccess.Item> obtenerInventario() {
        return itemRepository.findAll();
    }
}

