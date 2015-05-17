package Biblioteca;

import java.util.ArrayList;

public class User {

    private String name;
    private String type;
    protected ArrayList<Book> livros;
    protected int booksLimit;
    protected int timeLimit;

    public User(String name, String type) {
        this.name = name;
        this.type = type;
    }

}

