package com.example.magicalarena.controller;

import com.example.magicalarena.model.Player;
import com.example.magicalarena.repository.PlayerRepository;
import com.example.magicalarena.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/arena")
public class ArenaController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchService matchService;

    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable Long id) throws Exception {
        return playerRepository.findById(id).orElseThrow(() -> new Exception("Player not found with id " + id));
    }

    @PostMapping("/matches")
    public String startMatch(@RequestParam Long firstPlayerId, @RequestParam Long secondPlayerId) {
        return matchService.startMatch(firstPlayerId, secondPlayerId);
    }
}
