package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Vicente
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Usuario.POR_MATRICULA_E_SENHA, query = "select u from Usuario u where u.matricula = :matricula and u.senha = :senha"),
    @NamedQuery(name = Usuario.TODOS_USUARIOS, query = "select u from Usuario u where u.tipoUsuario = 0"),
    @NamedQuery(name = Usuario.USUARIO_POR_ID, query = "select u from Usuario u where u.id = :id ")
})
public class Usuario implements Serializable {

    public static final String POR_MATRICULA_E_SENHA = "Usuario.porMatriculaeSenha";
    public static final String TODOS_USUARIOS = "Usuario.todosUsuarios";
    public static final String GENEROS_POR_ID = "Usuario.generosPorIdUsuario";
    public static final String USUARIO_POR_ID = "Usuario.usuarioPorId";

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String senha;
    @Column(unique = true)
    private String matricula;
    private int tipoUsuario;
    //@Transient
    // private List<String> listaInteresses = new ArrayList();
    @ManyToMany
    private List<Genero> listaDeInteresses = new ArrayList();
    @OneToMany
    private List<Rec> listaDeRecs = new ArrayList();

    public List<Rec> getListaDeRecs() {
        return listaDeRecs;
    }

    public void setListaDeRecs(List<Rec> listaDeRecs) {
        this.listaDeRecs = listaDeRecs;
    }
   


    public List<Genero> getListaDeInteresses() {
        return listaDeInteresses;
    }

    public void setListaDeInteresses(List<Genero> listaDeInteresses) {
        this.listaDeInteresses = listaDeInteresses;
    }

    /* public List<String> getListaInteresses() {
        return listaInteresses;
    }

    public void setListaInteresses(List<String> listaInteresses) {
        this.listaInteresses = listaInteresses;
    }
     */
    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}
