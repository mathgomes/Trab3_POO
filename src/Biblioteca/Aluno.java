package Biblioteca;


import java.util.ArrayList;

/**
 * Classe aluno representa um usuario do tipo aluno
 */
public class Aluno extends User {

    public Aluno(String name, String type,int ID) {
        super(name,type,ID);
        livros = new ArrayList<>();
        booksLimit = 4;
        //15 dias em segundos
        timeLimit = 15*24*60*60;
    }

}
