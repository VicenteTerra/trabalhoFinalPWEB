package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Genero;
import model.Rec;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-21T16:45:51")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, String> name;
    public static volatile SingularAttribute<Usuario, String> matricula;
    public static volatile ListAttribute<Usuario, Genero> listaDeInteresses;
    public static volatile SingularAttribute<Usuario, Integer> tipoUsuario;
    public static volatile ListAttribute<Usuario, Rec> listaDeRecs;
    public static volatile SingularAttribute<Usuario, Long> id;

}