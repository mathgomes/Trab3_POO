package Biblioteca;

import java.util.ArrayList;

public class User {

    private String name;
    private String type;
    protected ArrayList<Book> livros;
    protected int booksLimit;
    protected int timeLimit;
    protected int bookCounter;

    public User(String name, String type) {
        this.name = name;
        this.type = type;
        this.bookCounter = 0;
    }
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public void incCounter() {
        bookCounter++;
    }
    public void decCounter() {
        bookCounter--;
    }
    public int getCounter() {
        return this.bookCounter;
    }
    public ArrayList<Book> getBooks() {
        System.out.println("Cheguei aqui");
        return this.livros;
    }
    public long getTimeLimit() {
        return this.timeLimit;
    }

}

