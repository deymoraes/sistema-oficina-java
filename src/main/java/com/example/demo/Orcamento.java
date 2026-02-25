package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- NOVOS CAMPOS ADICIONADOS ---
    private String numeroPedido;
    private String kmRecebida;

    // --- CAMPOS EXISTENTES ---
    private String cliente;
    private String veiculo;
    private String placa;
    private String telefone;
    private String email;
    private String descricaoProblema;

    @Column(updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    private Double valorTotal = 0.0;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "orcamento_id")
    private List<ItemOrcamento> itens = new ArrayList<>();

    public void calcularTotal() {
        if (this.itens != null && !this.itens.isEmpty()) {
            this.valorTotal = this.itens.stream().mapToDouble(ItemOrcamento::getSubtotal).sum();
        } else {
            this.valorTotal = 0.0;
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }

    public String getKmRecebida() { return kmRecebida; }
    public void setKmRecebida(String kmRecebida) { this.kmRecebida = kmRecebida; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getVeiculo() { return veiculo; }
    public void setVeiculo(String veiculo) { this.veiculo = veiculo; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDescricaoProblema() { return descricaoProblema; }
    public void setDescricaoProblema(String descricaoProblema) { this.descricaoProblema = descricaoProblema; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Double getValorTotal() { return valorTotal == null ? 0.0 : valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public List<ItemOrcamento> getItens() { return itens; }
    public void setItens(List<ItemOrcamento> itens) { this.itens = itens; }
}