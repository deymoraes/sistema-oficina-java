package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrcamentoController {

    @Autowired
    private OrcamentoRepository repository;

    @GetMapping("/orcamento")
    public String formulario(Model model) {
        model.addAttribute("orcamento", new Orcamento());
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvar(Orcamento orcamento, Model model) {
        repository.save(orcamento);
        model.addAttribute("orcamento", orcamento);
        return "resultado";
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("orcamentos", repository.findAll());
        return "lista";
    }

    // Método para excluir um orçamento pelo ID
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/lista"; // Redireciona de volta para a lista atualizada
    }

    // Método para abrir a tela de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}