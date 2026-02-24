package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrcamentoController {

    @Autowired
    private OrcamentoRepository repository;

    @GetMapping("/orcamento")
    public String novoOrcamento(Model model) {
        Orcamento orcamento = new Orcamento();
        orcamento.getItens().add(new ItemOrcamento());
        model.addAttribute("orcamento", orcamento);
        return "form-orcamento";
    }

    @PostMapping("/salvar")
    public String salvar(Orcamento orcamento) {
        try {
            // Proteção contra data nula
            if (orcamento.getDataCriacao() == null) {
                orcamento.setDataCriacao(LocalDateTime.now());
            }

            // Limpeza de itens vazios
            if (orcamento.getItens() != null) {
                orcamento.getItens().removeIf(item -> item.getDescricao() == null || item.getDescricao().trim().isEmpty());
            }

            orcamento.calcularTotal();
            repository.save(orcamento);
            return "redirect:/lista";

        } catch (Exception e) {
            System.err.println("==== ERRO CRÍTICO AO SALVAR ====");
            e.printStackTrace();
            return "redirect:/orcamento?erro";
        }
    }

    @GetMapping("/lista")
    public String listarOrcamentos(Model model) {
        List<Orcamento> orcamentos = repository.findAll();
        model.addAttribute("orcamentos", orcamentos);
        return "lista-orcamentos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/lista";
    }

    @GetMapping("/imprimir/{id}")
    public String imprimir(@PathVariable Long id, Model model) {
        Orcamento orcamento = repository.findById(id).orElse(new Orcamento());
        model.addAttribute("orcamento", orcamento);
        return "imprimir-orcamento";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}