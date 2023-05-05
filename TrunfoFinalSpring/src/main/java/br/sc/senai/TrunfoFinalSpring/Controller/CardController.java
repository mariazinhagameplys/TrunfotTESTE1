package br.sc.senai.TrunfoFinalSpring.Controller;

import br.sc.senai.TrunfoFinalSpring.Model.DTO.CardDTO;
import br.sc.senai.TrunfoFinalSpring.Model.Entity.Card;
import br.sc.senai.TrunfoFinalSpring.Service.CardService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/card")
@CrossOrigin
public class CardController {

    @Autowired
    private CardService cardservice;

    @PostMapping("/create")
    public ResponseEntity<Card> create (@RequestBody CardDTO cardDTO){
        Card card = new Card();
        BeanUtils.copyProperties(cardDTO,card);
        return ResponseEntity.ok(cardservice.createUpdate(card));
    }

    @GetMapping("/getCards")
    public ResponseEntity<List<Card>> searchAll(){
        return ResponseEntity.ok(cardservice.searchAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        cardservice.delete(id);
    }

    @GetMapping("/getCard/{id}")
    public ResponseEntity<Card> searchOne(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cardservice.searchOne(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody CardDTO cardDTO){
        Card card = cardservice.searchOne(id);
        BeanUtils.copyProperties(cardDTO,card);
        return ResponseEntity.ok(cardservice.update(card));
    }

}
