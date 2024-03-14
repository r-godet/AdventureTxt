package com.discord.bot.Inventary;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InventoryServices {

    private final ItemRepository itemRepository;

    @Autowired
    public InventoryServices(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    private InventoryServices is;
    public void agregarItem(String nombre) {

    }

    public void utilizarItem(){

    }

    public List<Inventary> obtenerInventario() {
        return itemRepository.findAll();
    }

    private String describirObjetoDelInventario(Inventary objeto) {
        return "Nombre: " + objeto.getName() + ", Cantidad: " + objeto.getQuantity();
    }

    public String abrirInventario(MessageCreateEvent event){
        List<Inventary> inventario = is.obtenerInventario();
        if (inventario == null || inventario.isEmpty()) {
           Objects.requireNonNull(event.getMessage().getChannel().block()).createMessage("El inventario está vacío").block();
        } else {
            String mensajeInventario = inventario.stream()
                    .map(this::describirObjetoDelInventario)
                    .collect(Collectors.joining(", "));
            Objects.requireNonNull(event.getMessage().getChannel().block()).createMessage("Objetos en el inventario: " + mensajeInventario).block();
        }
        return " ";
    }
}

