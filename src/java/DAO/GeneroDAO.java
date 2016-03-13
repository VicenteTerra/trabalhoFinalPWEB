/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Genero;
import model.Livro;

/**
 *
 * @author Vicente
 */
public interface GeneroDAO {

    public void salvar(Genero novoGenero);

    public void remover(long id);

    public List<Genero> todas();
    
    public List<String> todosporNome();

   
    
   
    
}
