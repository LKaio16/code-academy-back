package com.devwebback.devweb.repositories;

import com.devwebback.devweb.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Aluno a WHERE a.matricula = :matricula")
    boolean verificaMatricula(@Param("matricula") int matricula);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Aluno a WHERE a.email = :email")
    boolean verificaEmail(@Param("email") String email);
}
