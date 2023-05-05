package br.sc.senai.TrunfoFinalSpring.Service;

import br.sc.senai.TrunfoFinalSpring.Model.Entity.Player;
import br.sc.senai.TrunfoFinalSpring.Repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player create(Player player){
        return playerRepository.save(player);
    }

    public List<Player> searchAll(){
        return playerRepository.findAll();
    }
}
