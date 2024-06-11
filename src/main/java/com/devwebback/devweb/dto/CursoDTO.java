package com.devwebback.devweb.dto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursoDTO {
    private String nome;
    private String descricao;
    private String linkImg;
    private long professorId;
    private List<Long> alunos;
}
