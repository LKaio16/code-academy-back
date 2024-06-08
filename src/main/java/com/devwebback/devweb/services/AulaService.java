package com.devwebback.devweb.services;

import com.devwebback.devweb.model.Aula;
import com.devwebback.devweb.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    // Adiciona uma nova aula ou atualiza uma existente
    public Aula saveAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    // Lista todas as aulas de um curso específico
    public List<Aula> findAulasByCursoId(Long cursoId) {
        return aulaRepository.findByCursoId(cursoId);
    }

    // Deleta uma aula por ID
    public boolean deleteAulaById(Long id) {
        if (aulaRepository.existsById(id)) {
            aulaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}