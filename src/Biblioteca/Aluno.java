package Biblioteca;


import java.util.ArrayList;

public class Aluno extends User {

    public Aluno(String name, String type) {
        super(name,type);
        livros = new ArrayList<>();
        booksLimit = 4;
        timeLimit = 15*24*60*60;
    }

}
