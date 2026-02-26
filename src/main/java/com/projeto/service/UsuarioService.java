package com.projeto.service;

import com.projeto.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.repository.UsuarioRepository;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository repository;

    public UsuarioService() {
        this.repository = new UsuarioRepository();
    }

    public void registrarUsuario(String nome, String email) {
        if (email == null || !email.contains("@")) {
            System.out.println("Erro: Email inválido.");
            return;
        }
        Usuario novoUsuario = new Usuario(nome, email);
        repository.salvar(novoUsuario);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return repository.listarTodos();
    }

    //Aplicando para editar no frontend para que no controller eu consiga mandar para o front um unico usuario.
    public Usuario buscarUsuarioPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public void editarUsuario(Long id, String novoNome, String novoEmail) {
        Usuario usuario = new Usuario(id, novoNome, novoEmail);
        repository.atualizar(usuario);
    }

    public void removerUsuario(Long id) {
        repository.deletar(id);
    }
}