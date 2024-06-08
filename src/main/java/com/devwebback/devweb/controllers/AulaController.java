package com.devwebback.devweb.controllers;

import com.devwebback.devweb.model.Aula;
import com.devwebback.devweb.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aula")
public class AulaController {
    @Autowired
    private AulaService aulaService;

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarAula(@RequestBody Aula aula) {
        try {
            Aula aulaSalva = aulaService.saveAula(aula);
            return ResponseEntity.status(HttpStatus.CREATED).body(aulaSalva);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Aula>> listarAulasPorCursoId(@PathVariable Long cursoId) {
        List<Aula> aulas = aulaService.findAulasByCursoId(cursoId);
        return ResponseEntity.ok(aulas);
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