package model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Vicente
 */
@Entity
@NamedQueries({@NamedQuery(name = Usuario.POR_MATRICULA_E_SENHA, query = "select u from Usuario u where u.matricula = :matricula and u.senha = :senha"),
@NamedQuery(name = Usuario.TODOS_USUARIOS, query = "select u from Usuario u where u.tipoUsuario = 0")
})
public class Usuario implements Serializable {

   
    public static final String POR_MATRICULA_E_SENHA = "Usuario.porMatriculaeSenha";
    public static final String TODOS_USUARIOS = "Usuario.todosUsuarios";
    

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String senha;
    @Column(unique = true)
    private String matricula;
    private int tipoUsuario;

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
