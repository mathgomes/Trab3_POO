package Biblioteca;

/**
 * Classe abstrata que representa um livro generico
 */
public class Book {

    // Nome do livro
    public String name;
    // Tipo do livro
    public String type;
    // Data ( em segundos desde 1970) que foi pego emprestado
    protected long borrowedDate;
    // Checa se ja esta pego ou nao
    protected boolean isTaken;

    public Book(String name, String type) {
        this.name = name;
        this.type = type;
        this.isTaken = false;
    }
    // Get para o nome e o tipo
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    // Set e Get para a data em que foi pego
    public void setBorrowedDate(long date) {
        this.borrowedDate = date;
    }
    public long getBorrowedDate() {
        return this.borrowedDate;
    }
    // Set e Get para o status ( se esta pego ou nao )
    public void setStatus(boolean status) {
        this.isTaken = status;
    }
    public boolean getStatus() {
        return this.isTaken;
    }
}
