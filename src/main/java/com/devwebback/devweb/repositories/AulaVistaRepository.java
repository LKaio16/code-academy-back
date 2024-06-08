package com.devwebback.devweb.repositories;

import com.devwebback.devweb.model.AulaVista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaVistaRepository extends JpaRepository<AulaVista, Long> {}

