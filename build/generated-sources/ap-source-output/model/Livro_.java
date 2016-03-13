package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Genero;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-13T18:30:57")
@StaticMetamodel(Livro.class)
public class Livro_ { 

    public static volatile SingularAttribute<Livro, String> titulo;
    public static volatile SingularAttribute<Livro, Long> id;
    public static volatile ListAttribute<Livro, Genero> listaDeGeneros;
    public static volatile SingularAttribute<Livro, String> autor;
    public static volatile SingularAttribute<Livro, String> descricao;

}