package com.devwebback.devweb.controllers;

import com.devwebback.devweb.dto.AulaDTO;
import com.devwebback.devweb.model.Aula;
import com.devwebback.devweb.model.Curso;
import com.devwebback.devweb.model.Professor;
import com.devwebback.devweb.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aula")
public class AulaController {
    @Autowired
    private AulaService aulaService;

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarAula(@RequestBody AulaDTO aulaDTO) {
        try {
            Aula aula = new Aula();

            aula.setTitulo(aulaDTO.getTitulo());
            aula.setDescricao(aulaDTO.getDescricao());
            aula.setUrl(aulaDTO.getUrl());

            aula.setCurso(Curso.builder().id(aulaDTO.getCursoId()).build());

            Aula aulaSalva = aulaService.saveAula(aula);
            return ResponseEntity.status(HttpStatus.CREATED).body(aulaSalva);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<AulaDTO>> listarAulasPorCursoId(@PathVariable Long cursoId) {
        List<Aula> aulas = aulaService.findAulasByCursoId(cursoId);
        List<AulaDTO> aulaDTOs = aulas.stream()
                .map(aula ->  AulaDTO.builder()
                        .id(aula.getId())
                        .titulo(aula.getTitulo())
                        .descricao(aula.getDescricao())
                        .url(aula.getUrl())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(aulaDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAulaPorId(@PathVariable Long id) {
        boolean isDeleted = aulaService.deleteAulaById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}