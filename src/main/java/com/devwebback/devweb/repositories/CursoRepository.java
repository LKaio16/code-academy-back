package com.devwebback.devweb.repositories;

import com.devwebback.devweb.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByProfessorId(Long professorId);

}

