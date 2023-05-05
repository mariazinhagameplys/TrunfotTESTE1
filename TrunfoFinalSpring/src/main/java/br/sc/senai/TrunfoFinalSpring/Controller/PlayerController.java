package br.sc.senai.TrunfoFinalSpring.Controller;

import br.sc.senai.TrunfoFinalSpring.Model.DTO.PlayerDTO;
import br.sc.senai.TrunfoFinalSpring.Model.Entity.Player;
import br.sc.senai.TrunfoFinalSpring.Service.PlayerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/player")
@NoArgsConstructor
@CrossOrigin
public class PlayerController {

    @Autowired
    private PlayerService playerservice;

    @PostMapping("/create")
    public ResponseEntity<Player> create(@RequestBody PlayerDTO playerDTO){
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO,player);
        return ResponseEntity.ok(playerservice.create(player));
    }

    @GetMapping("/getPlayers")
    public ResponseEntity<List<Player>> searchAll(){
        return ResponseEntity.ok(playerservice.searchAll());
    }

}
