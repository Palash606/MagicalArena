package com.example.magicalarena.service;

import com.example.magicalarena.model.Player;
import com.example.magicalarena.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MatchService {

    @Autowired
    private PlayerRepository playerRepository;

    private Random random = new Random();

    public String startMatch(Long firstPlayerId, Long secondPlayerId) {
        Player player1 = playerRepository.findById(firstPlayerId).orElseThrow();
        Player player2 = playerRepository.findById(secondPlayerId).orElseThrow();

        StringBuilder result = new StringBuilder();

        while (player1.getHealth() > 0 && player2.getHealth() > 0) {
            if (player1.getHealth() < player2.getHealth()) {
                fight(player1, player2, result);
                if (player2.getHealth() <= 0) {
                    result.append("Player 1 wins!!! \n");
                    break;
                }
                fight(player2, player1, result);
                if (player1.getHealth() <= 0) {
                    result.append("Player 2 wins!!! \n");
                    break;
                }
            } else {
                fight(player2, player1, result);
                if (player1.getHealth() <= 0) {
                    result.append("Player 2 wins!\n");
                    break;
                }
                fight(player1, player2, result);
                if (player2.getHealth() <= 0) {
                    result.append("Player 1 wins!\n");
                    break;
                }
            }
        }
        return result.toString();
    }

    private void fight(Player attacker, Player defender, StringBuilder result) {
        int attackRoll = rollDie();
        int defenceRoll = rollDie();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defenceStrength = defender.getStrength() * defenceRoll;

        int damageTaken = Math.max(0, attackDamage - defenceStrength);
        defender.setHealth(defender.getHealth() - damageTaken);

        result.append("Attacker rolls ").append(attackRoll)
                .append(", Defender rolls ").append(defenceRoll).append("\n");
        result.append("Attack damage: ").append(attackDamage)
                .append(", Defense strength: ").append(defenceStrength).append("\n");
        result.append("Defender health reduced by ").append(damageTaken)
                .append(" to ").append(defender.getHealth()).append("\n\n");
    }

    private Integer rollDie() {
        return random.nextInt(6) + 1;
    }
}

