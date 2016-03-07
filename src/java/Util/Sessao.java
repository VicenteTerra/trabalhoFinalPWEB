/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Controler.ControladorDeUsuario;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author Vicente
 */
public class Sessao {
      public static Usuario obterUsuarioSessao() {
        FacesContext context = FacesContext.getCurrentInstance();
        ControladorDeUsuario usuarioBean = (ControladorDeUsuario) context.getELContext().getELResolver().getValue(context.getELContext(), null, "userControl");
        if (usuarioBean != null) {
            return usuarioBean.getUsuarioSessao();
        }
        return null;
    }
}
