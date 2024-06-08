package com.devwebback.devweb.controllers;

import com.devwebback.devweb.model.Curso;
import com.devwebback.devweb.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarCurso(@RequestBody Curso curso) {
        try {
            Curso cursoSalvo = cursoService.saveCurso(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodosCursos() {
        List<Curso> cursos = cursoService.listarTodosCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> encontrarCursoPorId(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.encontrarCursoPorId(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCursoPorId(@PathVariable Long id) {
        boolean deletado = cursoService.deletarCursoPorId(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{cursoId}/adicionarAluno/{alunoId}")
    public ResponseEntity<?> adicionarAlunoAoCurso(@PathVariable Long cursoId, @PathVariable Long alunoId) {
        try {
            Curso cursoAtualizado = cursoService.adicionarAlunoAoCurso(cursoId, alunoId);
            return ResponseEntity.ok(cursoAtualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
