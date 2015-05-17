package Biblioteca;


import java.util.ArrayList;

public class Aluno extends User {

    public Aluno(String name, String type) {
        super(name,type);
        livros = new ArrayList<Book>();
        booksLimit = 4;
        timeLimit = 15;
    }

}
