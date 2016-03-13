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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Vicente
 */
@Entity
@NamedQueries({
@NamedQuery(name = Genero.TODOS_GENEROS, query = "select u from Genero u ")
})
public class Genero implements Serializable {
    public static final String TODOS_GENEROS = "Genero.todosGeneros";
  
  
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    /*@ManyToOne
    private Usuario user = null;
    @ManyToOne
    private Livro livro = null;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
