package br.sc.senai.TrunfoFinalSpring.Model.DTO;

import br.sc.senai.TrunfoFinalSpring.Model.Entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {

    private String name;
    private String password;
}
