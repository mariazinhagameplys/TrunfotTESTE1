package br.sc.senai.TrunfoFinalSpring.Repository;

import br.sc.senai.TrunfoFinalSpring.Model.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
}
