/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.DAO;

import DAO.GeneroDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Genero;
import model.Livro;

/**
 *
 * @author Vicente
 */
public class JPAGenero implements GeneroDAO,Serializable {
    
     public void salvar(Genero genero) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(genero);
        em.getTransaction().commit();
        em.close();
    }
    
     public List<Genero> todas() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Genero> tq = em.createNamedQuery(Genero.TODOS_GENEROS,
                Genero.class);
        
        List<Genero> generos = tq.getResultList();
        em.close();
        return generos;
    }

    public void remover(long livroId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Genero genero = em.find(Genero.class, livroId);
        if (genero != null) {
            em.getTransaction().begin();
            em.remove(genero);
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public List<String> todosporNome() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
       
        List<String> generos = em.createQuery("select distinct u.nome from Genero u").getResultList();
        
        em.close();
        return generos;
        
    }

   

    
    
}
