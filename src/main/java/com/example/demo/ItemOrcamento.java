package com.example.demo;

import jakarta.persistence.*;

@Entity
public class ItemOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Integer quantidade = 0;
    private Double valorUnitario = 0.0;

    public ItemOrcamento() {}

    public Double getSubtotal() {
        if (this.quantidade != null && this.valorUnitario != null) {
            return this.quantidade * this.valorUnitario;
        }
        return 0.0;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(Double valorUnitario) { this.valorUnitario = valorUnitario; }
}