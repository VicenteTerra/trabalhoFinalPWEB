/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Rec;
import model.Usuario;

/**
 *
 * @author Vicente
 */
public interface RecDAO {

    public List<Rec> todas(Long usuarioId);

    public void salvar(Rec rec);
    public void carregarRecs(Usuario usuarioSessao);
    public void remover(Long id);
}
