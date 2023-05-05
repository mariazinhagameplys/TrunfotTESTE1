package br.sc.senai.TrunfoFinalSpring.Service;

import br.sc.senai.TrunfoFinalSpring.Model.Entity.Card;
import br.sc.senai.TrunfoFinalSpring.Repository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Card createUpdate(Card card){
        return cardRepository.save(card);
    }

    public List<Card> searchAll(){
        return cardRepository.findAll();
    }

    public void delete(Long id){
        cardRepository.deleteById(id);
    }

    public Card update(Card card){
        return cardRepository.save(card);
    }

    public Card searchOne(Long id){
        return cardRepository.findById(id).get();
    }
}
