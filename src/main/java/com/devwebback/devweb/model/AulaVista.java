package com.devwebback.devweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aulas_vistas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaVista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    @Column(nullable = false)
    private boolean concluida;
}
