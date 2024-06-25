package com.example.apispringtad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @ElementCollection
    private List<String> coordenadas;
    @OneToMany
    private List<Sensor> sensores;
    @OneToMany
    private List<Atuador> atuadores;
}
