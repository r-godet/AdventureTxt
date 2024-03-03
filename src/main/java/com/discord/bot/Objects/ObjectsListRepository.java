package com.discord.bot.Objects;

import com.discord.bot.Objects.ObjectsList;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ObjectsListRepository extends JpaRepository<ObjectsList, Long> {
}
