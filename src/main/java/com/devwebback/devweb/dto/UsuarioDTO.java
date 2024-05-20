package com.devwebback.devweb.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;

    private String nome;

    private int matricula;

    private String email;

    private String senha;

    private String linkImg;

    private boolean isAluno;
    private boolean isProfessor;
}
