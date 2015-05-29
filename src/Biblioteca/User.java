package Biblioteca;

import java.util.ArrayList;

/**
 * Classe abstrata que representa um usuario generico
 */
public abstract class User {

    // ID do usuario
    private int ID;
    // nome do usuario
    private String name;
    // tipo do usuario
    private String type;
    // lista de livros que ele possui alugado
    protected ArrayList<Book> livros;
    // limite de livros que ele pode ter
    protected int booksLimit;
    // limite de tempo que um livro pode permanecer com ele
    protected int timeLimit;
    // contador para a quantidade atual de livros que ele possui
    protected int bookCounter;
    // boleano para checar se ele esta suspenso
    protected boolean isSuspended;
    // tempo de suspensao
    protected long suspensionTime;
    // data que foi suspenso
    protected long suspendeddDate;


    public User(String name, String type, int ID) {
        this.name = name;
        this.type = type;
        this.bookCounter = 0;
        this.isSuspended = false;
        setID(ID);
    }
    // set e get para o nome e o tipo  e o IDdo usuario
    public int getID() {
        return this.ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    // incrementa e decrementa a contagem atual de livros
    public void incCounter() {
        bookCounter++;
    }
    public void decCounter() {
        bookCounter--;
    }
    // retorna o vetor de livros alugados
    public ArrayList<Book> getBooks() {
        return this.livros;
    }
    // get para o tempo limite
    public long getTimeLimit() {
        return this.timeLimit;
    }
    // set para o status
    public void setStatus(boolean status) {
        this.isSuspended = status;
    }
    // set e get para o temp de suspensao
    public void setSuspensionTime(long time) {
        this.suspensionTime = time;
    }
    public long getSuspensionTime() {
        return this.suspensionTime;
    }
    // set e get para a data de suspensao
    public void setSuspendedDate(long time) {
        this.suspendeddDate = time;
    }
    public long getSuspendedDate() {
        return this.suspendeddDate;
    }
}

