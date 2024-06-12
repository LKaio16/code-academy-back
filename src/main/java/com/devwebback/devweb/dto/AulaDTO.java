package com.devwebback.devweb.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AulaDTO {
    private Long id;
    private String titulo;
    private String url;
    private boolean aulaVista;
    private String descricao;
    private long cursoId;
}
