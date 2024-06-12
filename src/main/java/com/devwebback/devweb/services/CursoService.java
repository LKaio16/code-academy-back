package com.devwebback.devweb.services;

import com.devwebback.devweb.model.Aluno;
import com.devwebback.devweb.model.Aula;
import com.devwebback.devweb.model.Curso;
import com.devwebback.devweb.repositories.AlunoRepository;
import com.devwebback.devweb.repositories.AulaRepository;
import com.devwebback.devweb.repositories.AulaVistaRepository;
import com.devwebback.devweb.repositories.CursoRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    private AulaVistaRepository aulaVistaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> listarTodosCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> encontrarCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Transactional
    public boolean deletarCursoPorId(Long id) {
        List<Aula> aulas = aulaRepository.findByCursoId(id);
        for (Aula aula : aulas) {
            // Deletar todas as entradas de AulaVista associadas à aula
            aulaVistaRepository.deleteByAula(aula);
            aulaRepository.deleteById(aula.getId());
        }
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        Curso curso = cursoExistente.get();
        curso.setAlunos(null);
        cursoRepository.save(curso);
        if (cursoExistente.isPresent()) {
            cursoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Curso> encontrarCursosPorProfessorId(Long professorId) {
        return cursoRepository.findByProfessorId(professorId);
    }

    public Curso updateCurso(Curso curso) {
        Curso cursoExistente = cursoRepository.findById(curso.getId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        cursoExistente.setNome(curso.getNome());
        cursoExistente.setDescricao(curso.getDescricao());
        cursoExistente.setLinkImg(curso.getLinkImg());
        cursoExistente.setProfessor(curso.getProfessor());

        return cursoRepository.save(cursoExistente);
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