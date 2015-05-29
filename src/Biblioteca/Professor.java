package Biblioteca;


import java.util.ArrayList;

/**
 * Classe professor representa um usuario do tipo professor
 */
public class Professor extends User{

    public Professor(String name, String type, int ID) {
        super(name,type,ID);
        livros = new ArrayList<>();
        booksLimit = 6;
        timeLimit = 60*24*60*60;
    }

}

