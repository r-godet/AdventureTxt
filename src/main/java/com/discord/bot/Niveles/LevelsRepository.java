package com.discord.bot.Niveles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelsRepository extends JpaRepository<Levels, Long> {
    @Override
    long count();
}
