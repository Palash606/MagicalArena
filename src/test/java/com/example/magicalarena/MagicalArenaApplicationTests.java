package com.example.magicalarena;

import com.example.magicalarena.controller.ArenaController;
import com.example.magicalarena.model.Player;
import com.example.magicalarena.repository.PlayerRepository;
import com.example.magicalarena.service.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MagicalArenaApplicationTests {

    @Autowired
    private ArenaController arenaController;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchService matchService;

    @Test
    void contextLoads() {
        assertThat(arenaController).isNotNull();
        assertThat(playerRepository).isNotNull();
        assertThat(matchService).isNotNull();
    }

    @Test
    void testCreatePlayer() {
        Player player = new Player(50, 5, 10);
        Player savedPlayer = playerRepository.save(player);
        assertThat(savedPlayer.getId()).isNotNull();
        assertThat(savedPlayer.getHealth()).isEqualTo(50);
        assertThat(savedPlayer.getStrength()).isEqualTo(5);
        assertThat(savedPlayer.getAttack()).isEqualTo(10);
    }

    @Test
    void testStartMatch() {
        Player player1 = playerRepository.save(new Player(50, 5, 10));
        Player player2 = playerRepository.save(new Player(100, 10, 5));
        String result = matchService.startMatch(player1.getId(), player2.getId());
        assertThat(result).contains("wins");
    }
}
