package com.devwebback.devweb.controllers;

import com.devwebback.devweb.dto.CursoDTO;
import com.devwebback.devweb.dto.ProfessorDTO;
import com.devwebback.devweb.model.Curso;
import com.devwebback.devweb.model.Professor;
import com.devwebback.devweb.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarCurso(@RequestBody CursoDTO cursoDTO) {
        try {
            Curso curso = new Curso();

            curso.setNome(cursoDTO.getNome());
            curso.setDescricao(cursoDTO.getDescricao());
            curso.setLinkImg(cursoDTO.getLinkImg());
            curso.setProfessor(Professor.builder().id(cursoDTO.getProfessor().getId()).build());

            Curso cursoSalvo = cursoService.saveCurso(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<CursoDTO>> listarCursosPorProfessorId(@PathVariable Long professorId) {
        List<Curso> cursos = cursoService.encontrarCursosPorProfessorId(professorId);
        List<CursoDTO> cursosDTO = cursos.stream().map(curso -> CursoDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .descricao(curso.getDescricao())
                .linkImg(curso.getLinkImg())
                .professor(ProfessorDTO.builder()
                        .id(curso.getProfessor().getId())
                        .nome(curso.getProfessor().getNome())
                        // outros campos relevantes
                        .build())
                .alunos(curso.getAlunos().stream().map(aluno -> aluno.getId()).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
        return ResponseEntity.ok(cursosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        try {
            Curso curso = new Curso();

            curso.setId(id);
            curso.setNome(cursoDTO.getNome());
            curso.setDescricao(cursoDTO.getDescricao());
            curso.setLinkImg(cursoDTO.getLinkImg());
            curso.setProfessor(Professor.builder().id(cursoDTO.getProfessor().getId()).build());

            Curso cursoAtualizado = cursoService.updateCurso(curso);
            return ResponseEntity.ok(cursoAtualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarTodosCursos() {
        List<Curso> cursos = cursoService.listarTodosCursos();
        List<CursoDTO> cursosDTO = cursos.stream().map(curso -> CursoDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .descricao(curso.getDescricao())
                .linkImg(curso.getLinkImg())
                .professor(ProfessorDTO.builder()
                        .id(curso.getProfessor().getId())
                        .nome(curso.getProfessor().getNome())
                        // outros campos relevantes
                        .build())
                .alunos(curso.getAlunos().stream().map(aluno -> aluno.getId()).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
        return ResponseEntity.ok(cursosDTO);
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
