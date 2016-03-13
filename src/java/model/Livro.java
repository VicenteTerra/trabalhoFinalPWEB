/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 *
 * @author Vicente
 */
@Entity
@NamedQuery(name = Livro.TODOS_LIVROS, query = "select u from Livro u ")

public class Livro implements Serializable {

    public static final String TODOS_LIVROS = "Livro.todosLivros";

    @Id
    @GeneratedValue
    private long id;
    private String titulo;
    private String autor;
    private String descricao;
    @ManyToMany
    private List<Genero> listaDeGeneros = new ArrayList();

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Genero> getListaDeGeneros() {
        return listaDeGeneros;
    }

    public void setListaDeGeneros(List<Genero> listaDeGeneros) {
        this.listaDeGeneros = listaDeGeneros;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
