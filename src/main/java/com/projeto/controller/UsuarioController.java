package com.projeto.controller;

import com.projeto.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.projeto.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //Adicionando GetMapping para a azure identificar a página inicial
    @GetMapping("/")
    public String paginaInicial(){
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        var lista = service.buscarTodosUsuarios();
        model.addAttribute("usuarios", lista);
        return "lista_user";
    }

    //Salvar usuários
    @GetMapping("/usuarios_novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "form_user";
    }
    @PostMapping("/usuarios_salvar")
    public String salvarUsuario(@ModelAttribute Usuario usuario) {
        service.registrarUsuario(usuario.getNome(), usuario.getEmail());
        return "redirect:/usuarios";
    }

    //Editar usuários
    @GetMapping("/usuarios_editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        Usuario usuarioExistente = service.buscarUsuarioPorId(id);
        model.addAttribute("usuario", usuarioExistente);

        return "form_user";
    }
    @PostMapping("/usuarios_editar_salvar")
    public String salvarEdicao(@ModelAttribute Usuario usuario) {
        service.editarUsuario(usuario.getId(), usuario.getNome(), usuario.getEmail());
        return "redirect:/usuarios";
    }

    //Excluir usuário(s)
    @GetMapping("/usuarios_excluir/{id}")
    public String excluirUsuario(@PathVariable("id") Long id) {
        service.removerUsuario(id);
        return "redirect:/usuarios";
    }
}
