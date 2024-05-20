package com.devwebback.devweb.services;

import com.devwebback.devweb.dto.UsuarioDTO;
import com.devwebback.devweb.model.Aluno;
import com.devwebback.devweb.model.Professor;
import com.devwebback.devweb.repositories.AlunoRepository;
import com.devwebback.devweb.repositories.ProfessorRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Object login(String email, String senha) {
        Optional<Aluno> alunoOpt = alunoRepository.findByEmail(email);
        if (alunoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            if (passwordEncoder.matches(senha, aluno.getSenha())) {
                return montarUsuarioAluno(aluno);
            }
        }

        Optional<Professor> professorOpt = professorRepository.findByEmail(email);
        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();
            if (passwordEncoder.matches(senha, professor.getSenha())) {
                return montarUsuarioProfessor(professor);
            }
        }
        throw new RuntimeException("Nenhum usuario encontrado!");
    }


    public UsuarioDTO montarUsuarioAluno(Aluno aluno) {
        return UsuarioDTO.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .email(aluno.getEmail())
                .linkImg(aluno.getLinkImg())
                .matricula(aluno.getMatricula())
                .isAluno(true)
                .build();
    }

    public UsuarioDTO montarUsuarioProfessor(Professor professor) {
        return UsuarioDTO.builder()
                .id(professor.getId())
                .nome(professor.getNome())
                .email(professor.getEmail())
                .linkImg(professor.getLinkImg())
                .matricula(professor.getMatricula())
                .isProfessor(true)
                .build();
    }
}
