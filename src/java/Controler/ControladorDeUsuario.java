/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.RecDAO;
import DAO.UsuarioDAO;
import JPA.DAO.JPARec;
import JPA.DAO.JPAUsuario;
import Util.Mensagens;
import Util.Sessao;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import model.Rec;
import model.Usuario;

/**
 *
 * @author Vicente
 */
@ManagedBean(name = "userControl")
@SessionScoped
public class ControladorDeUsuario {

    private final UsuarioDAO usuarioDAO = new JPAUsuario();
    private final RecDAO recDAO = new JPARec();
    private Usuario usuarioSessao = null;
    private Usuario user = new Usuario();
    private String checkpassword;
    private List<Usuario> listUsers = new ArrayList();
    // private Usuario usuarioLogado = Sessao.obterUsuarioSessao();

    public String autentica() {
        usuarioSessao = usuarioDAO.autenticaMatriculaeSenha(user.getMatricula(),
                user.getSenha());

        Rec rec1 = new Rec();
        Rec rec2 = new Rec();
        Rec rec3 = new Rec();
        rec1.setRecNome("recs1");
        rec1.setUser(usuarioSessao);
        rec2.setRecNome("recs2");
        rec2.setUser(usuarioSessao);
        rec3.setRecNome("recs3");
        rec3.setUser(usuarioSessao);

        recDAO.salvar(rec1);
        recDAO.salvar(rec2);
        recDAO.salvar(rec3);

        user = new Usuario();
        if (usuarioSessao == null) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Email ou senha inválidos!",
                    null);
            return null;
        }
        if (usuarioSessao.getTipoUsuario() == 0) {
            return "indexUser.xhtml?faces-redirect=true";
        } else {
            carregarUsers();
            return "indexAdm.xhtml?faces-redirect=true";
        }

    }

    public String cadastro() throws RollbackException {

        try {
            if (user.getSenha().equals(checkpassword)) {
                user.setTipoUsuario(0);
                usuarioDAO.salvar(user);
                user = new Usuario();

                Mensagens.adicionarMensagem(
                        FacesMessage.SEVERITY_INFO,
                        "Inserção bem sucedida!",
                        null);

            } else {
                System.out.println("teste");
                Mensagens.adicionarMensagem(
                        FacesMessage.SEVERITY_ERROR,
                        "As senhas informadas não conferem!",
                        null);
            }
        } catch (RollbackException e) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Matricula já existe!",
                    null);
            user.setMatricula("");

            return "cadastro.xhtml?faces-redirect=true";

        }

        return "";
    }
    public String cadastroAdm() throws RollbackException {

        try {
            if (user.getSenha().equals(checkpassword)) {
                user.setTipoUsuario(0);
                usuarioDAO.salvar(user);
                user = new Usuario();

                Mensagens.adicionarMensagem(
                        FacesMessage.SEVERITY_INFO,
                        "Inserção bem sucedida!",
                        null);
                carregarUsers();
                return "indexAdm.xhtml?faces-redirect=true";
            } else {
                Mensagens.adicionarMensagem(
                        FacesMessage.SEVERITY_ERROR,
                        "As senhas informadas não conferem!",
                        null);
            }
        } catch (RollbackException e) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Matricula já existe!",
                    null);
            user.setMatricula("");

            return "cadastro.xhtml?faces-redirect=true";

        }

        return "";
    }
    public String remover(Usuario user) {
        usuarioDAO.remover(user.getId());

        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Usuario excluido com sucesso!", null);
        carregarUsers();
        return "indexAdm.xhtml?faces-redirect=true";
    }

    public String logoff() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        user = new Usuario();
        return "index.xhtml?faces-redirect=true";
    }
    @PostConstruct
    public void carregarUsers() {
        listUsers = usuarioDAO.todas();
    }

    public List<Usuario> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<Usuario> listUsers) {
        this.listUsers = listUsers;
    }

    public String getCheckpassword() {
        return checkpassword;
    }

    public void setCheckpassword(String checkpassword) {
        this.checkpassword = checkpassword;
    }

    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
