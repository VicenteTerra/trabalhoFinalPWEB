/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Genero;
import model.Rec;
import model.Usuario;

/**
 *
 * @author Vicente
 */
public interface UsuarioDAO {

    public void salvar(Usuario usuario);

    public Usuario autenticaMatriculaeSenha(String matricula, String senha);

    public void remover(Long iduser);

    public List<Usuario> todas();

    public List<Genero> generosPorId(long id);

    public void atualizaUsuario(Usuario user);
}
