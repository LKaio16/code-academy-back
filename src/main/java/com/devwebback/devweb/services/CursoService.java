package com.devwebback.devweb.services;

import com.devwebback.devweb.model.Aluno;
import com.devwebback.devweb.model.Curso;
import com.devwebback.devweb.repositories.AlunoRepository;
import com.devwebback.devweb.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    // Salva ou atualiza um curso
    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Lista todos os cursos
    public List<Curso> listarTodosCursos() {
        return cursoRepository.findAll();
    }

    // Encontra um curso por ID
    public Optional<Curso> encontrarCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    // Deleta um curso por ID
    public boolean deletarCursoPorId(Long id) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isPresent()) {
            cursoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Curso adicionarAlunoAoCurso(Long cursoId, Long alunoId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);

        if (cursoOpt.isPresent() && alunoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            Aluno aluno = alunoOpt.get();
            curso.getAlunos().add(aluno);
            return cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Curso ou Aluno não encontrado");
        }
    }
}