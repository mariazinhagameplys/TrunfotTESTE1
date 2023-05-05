package br.sc.senai.TrunfoFinalSpring.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@Table (name = "TB_PLAYER")
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @Column(name = "ID_PLAYER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;

    @Column(name = "NAME_PLAYER")
    private String nameP;

    private String password;


    public void setName(String john) {
    }
}
