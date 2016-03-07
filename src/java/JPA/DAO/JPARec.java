/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.DAO;

import DAO.RecDAO;
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
public class JPARec implements RecDAO, Serializable {

    public void salvar(Rec rec) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(rec);
        em.getTransaction().commit();
        em.close();
    }

    public List<Rec> todas(Long usuarioId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Rec> tq = em.createNamedQuery(Rec.TODAS_RECOMENDACOES,
                Rec.class);
        tq.setParameter("id_user", usuarioId);
        List<Rec> recs = tq.getResultList();
        em.close();
        return recs;
    }

    public void remover(Long recId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Rec rec = em.find(Rec.class, recId);
        if (rec != null) {
            em.getTransaction().begin();
            em.remove(rec);
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public void carregarRecs(Usuario usuarioSessao) {

    }

    

}
