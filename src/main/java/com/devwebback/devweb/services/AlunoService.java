package com.devwebback.devweb.services;

import com.devwebback.devweb.model.Aluno;
import com.devwebback.devweb.repositories.AlunoRepository;
import com.devwebback.devweb.repositories.ProfessorRepository;
import com.devwebback.devweb.util.GeradorMatricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    private int gerarMatriculaUnica() {
        int matricula;
        do {
            matricula = GeradorMatricula.gerarMatricula();
        } while (alunoRepository.verificaMatricula(matricula));
        return matricula;
    }

    public Aluno salvarAluno(Aluno aluno) {
        if (professorRepository.verificaEmail(aluno.getEmail()) || alunoRepository.verificaEmail(aluno.getEmail())) {
            throw new RuntimeException("Email já cadastrado!");
        }
        aluno.setMatricula(gerarMatriculaUnica());
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> encontrarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id) {
        alunoRepository.deleteById(id);
    }


}


