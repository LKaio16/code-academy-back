package com.devwebback.devweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int matricula;

    @Column(nullable = false)
    private String descricao;

    @Column
    private String linkImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToMany(mappedBy = "cursos")
    private List<Aluno> alunos = new ArrayList<>();

    @OneToMany(mappedBy = "curso")
    private List<Aula> aulas = new ArrayList<>();
}
