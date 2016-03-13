/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.GeneroDAO;
import DAO.LivroDAO;
import DAO.RecDAO;
import DAO.UsuarioDAO;
import JPA.DAO.JPAGenero;
import JPA.DAO.JPALivro;
import JPA.DAO.JPARec;
import JPA.DAO.JPAUsuario;
import Util.Mensagens;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import model.Genero;
import model.Livro;
import model.Rec;

/**
 *
 * @author Vicente
 */
@ManagedBean(name = "livroControl")
public class ControladorDeLivros {

    private final LivroDAO livroDAO = new JPALivro();
    private final GeneroDAO generoDAO = new JPAGenero();
    private List<Livro> listaLivro = new ArrayList();
    private Livro novoLivro = new Livro();
   // private List<Genero> listaGeneroLivro = new ArrayList();
    private List<Genero> listaTodosGeneros = new ArrayList();
    private List<String> listaNome = generoDAO.todosporNome();
    private String[] selectedGeneros;
    private Livro selectedLivro;
    private List<Genero> interesses = new ArrayList();

    public String salvar() {

        /* for(String a : selectedGeneros){
             novoLivro.getListaGeneros().add(a);
        }*/
        listaTodosGeneros = generoDAO.todas();

        for (Genero a : listaTodosGeneros) {
            for (String b : selectedGeneros) {
                if (a.getNome().equals(b)) {
                    novoLivro.getListaDeGeneros().add(a);
                }
            }
        }

        livroDAO.salvar(novoLivro);

        /* for (String a : listaNome) {
            for (String b : selectedGeneros) {
                if (a.equals(b)) {
                    Genero novoGenero = new Genero();
                    novoGenero.setNome(a);
                    novoGenero.setLivro(novoLivro);
                    generoDAO.salvar(novoGenero);
                }
            }
        }*/
        /// listaGeneroLivro = livroDAO.generosPorId(novoLivro.getId());
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Livro Cadastrado com sucesso!", null);
        novoLivro = new Livro();

        return "livroView.xhtml?faces-redirect=true";
    }

    public String remover(Livro livro) {
        livroDAO.remover(livro.getId());
        carregarRecs();
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Livro excluido com sucesso!", null);

        return "livroView.xhtml?faces-redirect=true";
    }

  /*  public List<Genero> carregarGeneros(long id) {
        listaGeneroLivro = livroDAO.generosPorId(novoLivro.getId());
        return listaGeneroLivro;
    }*/

    @PostConstruct
    public void carregarRecs() {
        listaLivro = livroDAO.todas();

    }

    public List<Genero> getListaTodosGeneros() {
        return listaTodosGeneros;
    }

    public void setListaTodosGeneros(List<Genero> listaTodosGeneros) {
        this.listaTodosGeneros = listaTodosGeneros;
    }

   /* public List<Genero> getListaGeneroLivro() {
        return listaGeneroLivro;
    }

    public void setListaGeneroLivro(List<Genero> listaGeneroLivro) {
        this.listaGeneroLivro = listaGeneroLivro;
    }*/

    public List<Livro> getListaLivro() {
        return listaLivro;
    }

    public void setListaLivro(List<Livro> listaLivro) {
        this.listaLivro = listaLivro;
    }

    public Livro getNovoLivro() {
        return novoLivro;
    }

    public void setNovoLivro(Livro novoLivro) {
        this.novoLivro = novoLivro;
    }

    public String[] getSelectedGeneros() {
        return selectedGeneros;
    }

    public void setSelectedGeneros(String[] selectedGeneros) {
        this.selectedGeneros = selectedGeneros;
    }

    public List<String> getListaNome() {
        return listaNome;
    }

    public void setListaNome(List<String> listaNome) {
        this.listaNome = listaNome;
    }

    public Livro getSelectedLivro() {
        return selectedLivro;
    }

    public void setSelectedLivro(Livro selectedLivro) {
        this.selectedLivro = selectedLivro;
    }

    public List<Genero> getInteresses() {
        return interesses;
    }

    public void setInteresses(List<Genero> interesses) {
        this.interesses = interesses;
    }

}
