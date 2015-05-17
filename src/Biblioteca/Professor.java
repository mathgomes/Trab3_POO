package Biblioteca;


import java.util.ArrayList;

public class Professor extends User{

    public Professor(String name, String type) {
        super(name,type);
        livros = new ArrayList<Book>();
        booksLimit = 6;
        timeLimit = 60;
    }

}

