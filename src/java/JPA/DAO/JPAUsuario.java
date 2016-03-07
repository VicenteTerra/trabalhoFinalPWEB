/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.DAO;

import DAO.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Rec;
import model.Usuario;

/**
 *
 * @author Vicente
 */
public class JPAUsuario implements UsuarioDAO, Serializable {

    public void salvar(Usuario usuario) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Usuario autenticaMatriculaeSenha(String matricula, String senha) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Usuario> tq = em.createNamedQuery(Usuario.POR_MATRICULA_E_SENHA,
                    Usuario.class);
            tq.setParameter("matricula", matricula);
            tq.setParameter("senha", senha);
            List<Usuario> lu = tq.getResultList();
            if (lu == null || lu.isEmpty()) {
                return null;
            }
            return lu.get(0);
        } finally {
            em.close();
        }
    }

    public void remover(Long userId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Usuario user = em.find(Usuario.class, userId);
        if (user != null) {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
        em.close();
    }
    public List<Usuario> todas() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Usuario> tq = em.createNamedQuery(Usuario.TODOS_USUARIOS,
                Usuario.class);
      //  tq.setParameter("id_user", usuarioId);
        List<Usuario> usuarios = tq.getResultList();
        em.close();
        return usuarios;
    }
}
