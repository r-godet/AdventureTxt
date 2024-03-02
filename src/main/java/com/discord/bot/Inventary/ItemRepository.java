package com.discord.bot.Inventary;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<AbstractReadWriteAccess.Item, Long> {
    // Aquí puedes definir métodos personalizados de consulta si lo necesitas
}

