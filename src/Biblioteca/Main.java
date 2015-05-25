package Biblioteca;

import static javafx.application.Application.launch;

/**
 * Classe Main que inicializa a biblioteca
 */
public class Main {
    public static void main(String []args) {

        Library library = new Library();
        // le o arquivo texto
        library.readCsvFile("information.csv");
        launch(Library.class, args);

    }
}
