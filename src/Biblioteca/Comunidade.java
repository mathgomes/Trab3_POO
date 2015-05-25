package Biblioteca;


import java.util.ArrayList;

/**
 * Classe comunidade representa um usuario da comunidade
 */
public class Comunidade extends User{

    public Comunidade(String name, String type) {
        super(name,type);
        livros = new ArrayList<>();
        booksLimit = 2;
        timeLimit = 15*24*60*60;
    }
}
