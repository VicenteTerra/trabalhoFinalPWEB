/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.DAO;

import DAO.LivroDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Genero;
import model.Livro;
import model.Rec;

/**
 *
 * @author Vicente
 */
public class JPALivro implements LivroDAO, Serializable {

    public void salvar(Livro livro) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(livro);
        em.getTransaction().commit();
        em.close();
    }

    public List<Livro> todas() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Livro> tq = em.createNamedQuery(Livro.TODOS_LIVROS,
                Livro.class);
        List<Livro> livros = tq.getResultList();
        em.close();
        return livros;
    }

    public void remover(long livroId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Livro livro = em.find(Livro.class, livroId);
        if (livro != null) {
            em.getTransaction().begin();
            em.remove(livro);
            em.getTransaction().commit();
        }
        em.close();
    }

   /* public List<Genero> generosPorId(long id) {

       EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Genero> tq = em.createNamedQuery(Genero.GENEROS_POR_ID_LIVRO,
                Genero.class);
        tq.setParameter("id",id);
        List<Genero> generos = tq.getResultList();
        em.close();
        return generos;

    }*/

}
