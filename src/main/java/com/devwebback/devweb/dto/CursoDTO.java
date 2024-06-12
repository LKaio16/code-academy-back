package com.devwebback.devweb.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursoDTO {
    private long id;
    private String nome;
    private String descricao;
    private String linkImg;
    private ProfessorDTO professor;
    private List<Long> alunos;
}
