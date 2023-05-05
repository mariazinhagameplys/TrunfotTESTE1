package br.sc.senai.TrunfoFinalSpring.Model.DTO;

import br.sc.senai.TrunfoFinalSpring.Model.Enum.Emotion;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private String name;
    private String description;
    private int depression;
    private int strength;
    private int luck;
    private int defense;
    private int hearts;
    @Enumerated(EnumType.STRING)
    private Emotion emotion;
}
