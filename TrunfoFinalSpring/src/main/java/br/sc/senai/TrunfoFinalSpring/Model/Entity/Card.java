package br.sc.senai.TrunfoFinalSpring.Model.Entity;

import br.sc.senai.TrunfoFinalSpring.Model.Enum.Emotion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "TB_CARD")
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @Column(name = "ID_CARD")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME_CARD")
    private String name;

    @Column(name = "DESCRIPTION_CARD")
    private String description;

    @Column(name = "DEPRESSION_CARD")
    @Min(1) @Max(5)
    private int depression;

    @Column(name = "STRENGTH_CARD")
    @Min(1) @Max(5)
    private int strength;

    @Column(name = "LUCK_CARD")
    @Min(1) @Max(5)
    private int luck;

    @Column(name = "DEFENSE_CARD")
    @Min(1) @Max(5)
    private int defense;

    @Column(name = "HEARTS_CARD")
    @Min(1) @Max(5)
    private int hearts;

    @Column(name = "EMOTION_CARD")
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

}
