/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Vicente
 */
@Entity
//@NamedQuery(name = Rec.TODAS_RECOMENDACOES, query = "select u from Rec u where u.user.id = :id_user ")

public class Rec implements Serializable {
    public static final String TODAS_RECOMENDACOES = "Rec.todasRecomendacoes";
    
    @Id @GeneratedValue
    private long id;
    private int recAvaliacao;
    @OneToOne
    private Livro livro;

   

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   

    public int getRecAvaliacao() {
        return recAvaliacao;
    }

    public void setRecAvaliacao(int recAvaliacao) {
        this.recAvaliacao = recAvaliacao;
    }
}
