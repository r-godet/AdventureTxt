package com.discord.bot.user;

import com.discord.bot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
}

