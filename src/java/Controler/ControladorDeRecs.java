/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.RecDAO;
import JPA.DAO.JPARec;
import Util.Mensagens;
import Util.Sessao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import model.Rec;
import model.Usuario;

/**
 *
 * @author Vicente
 */
@ManagedBean(name = "recsControl")
@SessionScoped

public class ControladorDeRecs {

    private List<Rec> listaRecs = new ArrayList();
    private final RecDAO recDAO = new JPARec();
    private Rec novaRec = new Rec();

    private Usuario usuarioLogado = Sessao.obterUsuarioSessao();

    public String salvar2() {

       
        recDAO.salvar(novaRec);

        //carregarRecs();
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Recomendação cadastrada com sucesso!", null);
        novaRec = new Rec();
        return "indexUser.xhtml?faces-redirect=true";
    }

    public String remover(Rec rec) {
        recDAO.remover(rec.getId());
       // carregarRecs();
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Recomendação excluida com sucesso!", null);

        return "indexUser.xhtml?faces-redirect=true";
    }

    public void salvar(Rec rec) {

        recDAO.salvar(rec);

        //carregarRecs();
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Recomendação cadastrada com sucesso!", null);

        //return "tarefas.xhtml?faces-redirect=true";
    }

    /*@PostConstruct
    public void carregarRecs() {
        listaRecs = recDAO.todas(usuarioLogado.getId());
    }*/

    public Rec getNovaRec() {
        return novaRec;
    }

    public void setNovaRec(Rec novaRec) {
        this.novaRec = novaRec;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<Rec> getListaRecs() {
        return listaRecs;
    }

    public void setListaRecs(ArrayList<Rec> listaRecs) {
        this.listaRecs = listaRecs;
    }

}
