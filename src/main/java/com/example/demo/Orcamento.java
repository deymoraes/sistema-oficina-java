package com.example.demo; // Mantenha o nome do pacote que aparece no seu IntelliJ

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity // Isso avisa ao Java que esta classe agora é uma tabela no banco de dados
public class Orcamento {

    @Id // Define que o 'id' é a chave única deste orçamento
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco vai contar 1, 2, 3... sozinho
    private Long id;

    private String cliente;
    private String veiculo;
    private String descricao;
    private BigDecimal valor;

    // --- ABAIXO ESTÃO OS GETTERS E SETTERS (AS PORTINHAS DE ACESSO) ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getVeiculo() { return veiculo; }
    public void setVeiculo(String veiculo) { this.veiculo = veiculo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}