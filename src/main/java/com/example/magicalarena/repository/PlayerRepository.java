package com.example.magicalarena.repository;

import com.example.magicalarena.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
