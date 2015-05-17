package Biblioteca;


import java.util.ArrayList;

public class Comunidade extends User{

    public Comunidade(String name, String type) {
        super(name,type);
        livros = new ArrayList<Book>();
        booksLimit = 2;
        timeLimit = 15;
    }
}
