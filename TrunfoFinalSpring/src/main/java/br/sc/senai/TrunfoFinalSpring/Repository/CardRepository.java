package br.sc.senai.TrunfoFinalSpring.Repository;

import br.sc.senai.TrunfoFinalSpring.Model.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
}
