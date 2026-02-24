package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

// Ao herdar de JpaRepository, o Spring cria sozinho todos os comandos de banco (SELECT, INSERT, etc)
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}