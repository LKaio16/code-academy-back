package com.devwebback.devweb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aula_vista")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AulaVista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;
}
