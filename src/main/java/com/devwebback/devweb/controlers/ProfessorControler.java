package com.devwebback.devweb.controlers;

import com.devwebback.devweb.model.Aluno;
import com.devwebback.devweb.model.Professor;
import com.devwebback.devweb.services.AlunoService;
import com.devwebback.devweb.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")

public class ProfessorControler {
    @Autowired
    private ProfessorService professorService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarProfessor(@RequestBody Professor professor){
        try {
            Professor professorSalvo = professorService.salvarProfessor(professor);
            return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarTodosProfessores() {
        List<Professor> professores = professorService.listarTodosProfessores();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> encontrarProfessorPorId(@PathVariable Long id) {
        Optional<Professor> professor = professorService.encontrarProfessorPorId(id);
        return professor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessorPorId(@PathVariable Long id) {
        professorService.deletarProfessorPorId(id);
        return ResponseEntity.noContent().build();
    }
}
