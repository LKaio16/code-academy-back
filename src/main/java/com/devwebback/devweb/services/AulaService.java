package com.devwebback.devweb.services;

import com.devwebback.devweb.dto.AulaDTO;
import com.devwebback.devweb.model.Aluno;
import com.devwebback.devweb.model.Aula;
import com.devwebback.devweb.model.AulaVista;
import com.devwebback.devweb.repositories.AulaRepository;
import com.devwebback.devweb.repositories.AulaVistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AulaVistaRepository aulaVistaRepository;

    public Aula saveAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    public List<Aula> findAulasByCursoId(Long cursoId) {
        return aulaRepository.findByCursoId(cursoId);
    }

    public boolean deleteAulaById(Long id) {
        if (aulaRepository.existsById(id)) {
            aulaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void marcarAulaComoVista(Long alunoId, Long aulaId) {
        AulaVista aulaVista = AulaVista.builder()
                .aluno(Aluno.builder().id(alunoId).build())
                .aula(Aula.builder().id(aulaId).build())
                .build();
        aulaVistaRepository.save(aulaVista);
    }
    public List<AulaDTO> listarAulasVistasPorAlunoECurso(Long alunoId, Long cursoId) {
        List<AulaVista> aulasVistas = aulaVistaRepository.findByAlunoIdAndAulaCursoId(alunoId, cursoId);
        List<AulaDTO> aulasVistasDTO = aulasVistas.stream()
                .map(aulaVista -> AulaDTO.builder()
                        .id(aulaVista.getAula().getId())
                        .titulo(aulaVista.getAula().getTitulo())
                        .descricao(aulaVista.getAula().getDescricao())
                        .url(aulaVista.getAula().getUrl())
                        .build())
                .collect(Collectors.toList());
        return aulasVistasDTO;
    }

    public AulaDTO verificarAulaVista(Long alunoId, Long aulaId) {
        Optional<Aula> aula = aulaRepository.findById(aulaId);
        AulaDTO aulaDTO = AulaDTO.builder().id(aula.get().getId()).aulaVista(aulaVistaRepository.existsByAlunoIdAndAulaId(alunoId, aulaId)).build();
        return aulaDTO;
    }


    public Aula updateAula(Long id, Aula aula) {
        Optional<Aula> existingAula = aulaRepository.findById(id);
        if (existingAula.isPresent()) {
            Aula updatedAula = existingAula.get();
            updatedAula.setTitulo(aula.getTitulo());
            updatedAula.setDescricao(aula.getDescricao());
            updatedAula.setUrl(aula.getUrl());
            return aulaRepository.save(updatedAula);
        } else {
            throw new RuntimeException("Aula não encontrada");
        }
    }
}