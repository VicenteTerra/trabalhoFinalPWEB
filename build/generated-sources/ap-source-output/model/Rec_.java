package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-10T17:53:58")
@StaticMetamodel(Rec.class)
public class Rec_ { 

    public static volatile SingularAttribute<Rec, String> recNome;
    public static volatile SingularAttribute<Rec, Long> id;
    public static volatile SingularAttribute<Rec, Integer> recAvaliacao;
    public static volatile SingularAttribute<Rec, Usuario> user;

}