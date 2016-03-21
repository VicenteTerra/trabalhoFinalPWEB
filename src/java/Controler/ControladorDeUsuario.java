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
import model.Genero;
import model.Livro;
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
    private final GeneroDAO generoDAO = new JPAGenero();
    private final LivroDAO livroDAO = new JPALivro();
    private Usuario usuarioSessao = null;
    private Usuario user = new Usuario();
    private String checkpassword;
    private List<Usuario> listUsers = new ArrayList();
    private List<Genero> listaTodosGeneros = new ArrayList();
    private String[] selectedInteresses;
    private List<String> listaNome = generoDAO.todosporNome();
    // private Usuario usuarioLogado = Sessao.obterUsuarioSessao();

    public String autentica() {
        usuarioSessao = usuarioDAO.autenticaMatriculaeSenha(user.getMatricula(),
                user.getSenha());

        recomendar(livroDAO.todas(), usuarioSessao);
        usuarioDAO.atualizaUsuario(usuarioSessao);
        for (Rec a : usuarioSessao.getListaDeRecs()) {
            System.out.println("recs : " + a.getLivro().getTitulo());
        }
        // user = new Usuario();
        if (usuarioSessao == null) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Email ou senha inválidos!",
                    null);
            return "index.xhtml?faces-redirect=true";
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

                listaTodosGeneros = generoDAO.todas();

                for (Genero a : listaTodosGeneros) {
                    for (String b : selectedInteresses) {
                        if (a.getNome().equals(b)) {
                            user.getListaDeInteresses().add(a);
                        }
                    }
                }
                recomendar(livroDAO.todas(), user);
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

    public void recomendar(List<Livro> livros, Usuario user) {
        boolean flag = false;
        for (Livro livro : livros) {

            for (Genero generoLivro : livro.getListaDeGeneros()) {
                for (Genero interesseUser : user.getListaDeInteresses()) {

                    if (generoLivro.getNome().equals(interesseUser.getNome())) {
                        for (Rec recsUser : user.getListaDeRecs()) {

                            if (recsUser.getLivro().getTitulo().equals(livro.getTitulo())) {
                                flag = true;
                            }
                        }
                        if (flag == false) {
                            Rec rec = new Rec();
                            rec.setLivro(livro);
                            recDAO.salvar(rec);
                            user.getListaDeRecs().add(rec);
                        }
                        flag = false;
                    }
                }
            }
        }

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

    public String atualizaDados() {
        System.out.println(" novo nome " + usuarioSessao.getName());
        usuarioDAO.atualizaUsuario(usuarioSessao);
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Usuario Alterado!", null);
        return "indexUser.xhtml?faces-redirect=true";

    }

    @PostConstruct
    public void carregarUsers() {
        listUsers = usuarioDAO.todas();
    }

    public List<Genero> getListaTodosGeneros() {
        return listaTodosGeneros;
    }

    public void setListaTodosGeneros(List<Genero> listaTodosGeneros) {
        this.listaTodosGeneros = listaTodosGeneros;
    }

    public List<String> getListaNome() {
        return listaNome;
    }

    public void setListaNome(List<String> listaNome) {
        this.listaNome = listaNome;
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

    public String[] getSelectedInteresses() {
        return selectedInteresses;
    }

    public void setSelectedInteresses(String[] selectedInteresses) {
        this.selectedInteresses = selectedInteresses;
    }

}
