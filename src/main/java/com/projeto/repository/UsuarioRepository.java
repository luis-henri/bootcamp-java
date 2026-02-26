package com.projeto.repository;

import com.projeto.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UsuarioRepository {

    // Cria a fábrica de conexões baseada no nome que demos no persistence.xml
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("socialDogsPU");

    public void salvar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(usuario);

        em.getTransaction().commit();
        em.close();
    }

    public List<Usuario> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Usuario> usuarios = em.createQuery("FROM Usuario", Usuario.class).getResultList();
        em.close();
        return usuarios;
    }

    public Usuario buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }

    public void atualizar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(usuario);

        em.getTransaction().commit();
        em.close();
    }

    public void deletar(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }

        em.getTransaction().commit();
        em.close();
    }
}