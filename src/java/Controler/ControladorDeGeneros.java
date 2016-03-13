/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.GeneroDAO;
import DAO.LivroDAO;
import JPA.DAO.JPAGenero;
import JPA.DAO.JPALivro;
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
@ManagedBean(name = "generoControl")
public class ControladorDeGeneros {

    private final GeneroDAO generoDAO = new JPAGenero();
    private List<Genero> listaGeneros = new ArrayList();
    private Genero novoGenero = new Genero();
    /*private String[] selectedCities;
    private final List<String> listaNome = generoDAO.todosporNome();*/ 

    public String salvar() {

        generoDAO.salvar(novoGenero);

        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Genero Cadastrado com sucesso!", null);
        novoGenero = new Genero();
        carregarGeneros();
        
        return "generoView.xhtml?faces-redirect=true";
    }

    public String remover(Genero genero) {
        generoDAO.remover(genero.getId());
        carregarGeneros();
        
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Genero excluido com sucesso!", null);
        return "generoView.xhtml?faces-redirect=true";
    }

   /* public List<String> getListaNome() {
        return listaNome;
    }*/

    @PostConstruct
    public void carregarGeneros() {
        listaGeneros = generoDAO.todas();
    }

    public List<Genero> getListaGeneros() {
        return listaGeneros;
    }

    public void setListaGeneros(List<Genero> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }

    public Genero getNovoGenero() {
        return novoGenero;
    }

    public void setNovoGenero(Genero novoGenero) {
        this.novoGenero = novoGenero;
    }

   /* public String[] getSelectedCities() {
        return selectedCities;
    }

    public void setSelectedCities(String[] selectedCities) {
        this.selectedCities = selectedCities;
    }*/

}
