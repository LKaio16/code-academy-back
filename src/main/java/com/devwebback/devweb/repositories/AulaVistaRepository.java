package com.devwebback.devweb.repositories;

import com.devwebback.devweb.model.Aula;
import com.devwebback.devweb.model.AulaVista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaVistaRepository extends JpaRepository<AulaVista, Long> {
    @Query("SELECT av FROM AulaVista av INNER JOIN av.aula a WHERE av.aluno.id = :alunoId AND a.curso.id = :cursoId")
    List<AulaVista> findByAlunoIdAndAulaCursoId(@Param("alunoId") Long alunoId, @Param("cursoId") Long cursoId);
    boolean existsByAlunoIdAndAulaId(Long alunoId, Long aulaId);

    void deleteByAula(Aula aula);


}

