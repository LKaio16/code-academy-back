package com.devwebback.devweb.services;

import com.devwebback.devweb.model.Aluno;
import com.devwebback.devweb.model.Professor;
import com.devwebback.devweb.repositories.AlunoRepository;
import com.devwebback.devweb.repositories.ProfessorRepository;
import com.devwebback.devweb.util.GeradorMatricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    private int gerarMatriculaUnica() {
        int matricula;
        do {
            matricula = GeradorMatricula.gerarMatricula();
        } while (professorRepository.verificaMatricula(matricula));
        return matricula;
    }

    public Professor salvarProfessor(Professor professor) {
        if (professorRepository.verificaEmail(professor.getEmail()) || alunoRepository.verificaEmail(professor.getEmail())) {
            throw new RuntimeException("Email já cadastrado!");
        }
        professor.setMatricula(gerarMatriculaUnica());
        return professorRepository.save(professor);
    }

    public List<Professor> listarTodosProfessores() {
        return professorRepository.findAll();
    }

    public Optional<Professor> encontrarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    public void deletarProfessorPorId(Long id) {
        professorRepository.deleteById(id);
    }
}
