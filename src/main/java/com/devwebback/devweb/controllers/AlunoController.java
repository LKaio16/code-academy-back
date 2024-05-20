package com.devwebback.devweb.controllers;

import com.devwebback.devweb.model.Aluno;
import com.devwebback.devweb.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")

public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarAluno(@RequestBody Aluno aluno) {
        try {
            Aluno alunoSalvo = alunoService.salvarAluno(aluno);
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodosAlunos() {
        List<Aluno> alunos = alunoService.listarTodosAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> encontrarAlunoPorId(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.encontrarAlunoPorId(id);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAlunoPorId(@PathVariable Long id) {
        alunoService.deletarAlunoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
