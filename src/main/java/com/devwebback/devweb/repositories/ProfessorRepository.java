package com.devwebback.devweb.repositories;

import com.devwebback.devweb.model.Aluno;
import com.devwebback.devweb.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Professor a WHERE a.matricula = :matricula")
    boolean verificaMatricula(@Param("matricula") int matricula);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Professor p WHERE p.email = :email")
    boolean verificaEmail(@Param("email") String email);

    Optional<Professor> findByEmail(String email);
}
