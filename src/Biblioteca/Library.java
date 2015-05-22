package Biblioteca;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;


public class Library extends Application{


    protected static List<User> Users = new ArrayList<>();
    protected static List<Book> Books = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane controlPanel = new GridPane();
        controlPanel.setHgap(0);
        controlPanel.setVgap(0);
        controlPanel.setGridLinesVisible(true);

        VBox container = new VBox();
        container.setSpacing(10);
        container.setPadding(new Insets(10, 10, 10, 10));
        container.setPrefWidth(700);

        Button buttons[] = new Button[8];
        for( int i = 0; i < 8; i ++) {
            buttons[i] = new Button();
            container.getChildren().add(buttons[i]);
            buttons[i].setMaxWidth(Double.MAX_VALUE);
        }

        TextArea area = new TextArea();
        TextField typingArea = new TextField();
        area.setEditable(false);
        Label l = new Label();

        buttons[0].setText("Cadastrar Usuario");
        buttons[1].setText("Cadastrar Livro");
        buttons[2].setText("Registrar Emprestimo");
        buttons[3].setText("Registrar Devolucao");
        buttons[4].setText("Listar Usuarios");
        buttons[5].setText("Listar Livros");
        buttons[6].setText("Listar Emprestimos");
        buttons[7].setText("Encerrar programa");

        buttons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

                l.setText("Digite o nome e o tipo(aluno,professor,comunidade) do usuario a ser cadastrado separados por virgula");
                typingArea.setOnAction(event -> {

                    String name = typingArea.getText();
                    typingArea.setText(null);
                    System.out.println("String "+ name);
                    String[] token = name.split(",");

                    String noSpaceToken = token[1].replaceAll("\\s","");
                    String userToken = token[0].toLowerCase();
                    String typeToken = noSpaceToken.toLowerCase();

                    if(Objects.equals(typeToken, "aluno")) {
                        String[] stringArray = Users.stream()
                                .filter(user -> Objects.equals(user.getName(), userToken))
                                .toArray(String[]::new);
                        if (stringArray != null) {
                            l.setText("Aluno ja existe");
                            return;
                        }
                        Users.add(new Aluno(userToken,typeToken));
                        l.setText("Aluno cadastrado com sucesso");
                    }
                    else if(Objects.equals(typeToken, "professor")) {
                        String[] stringArray = Users.stream()
                                .filter(user -> Objects.equals(user.getName(), userToken))
                                .toArray(String[]::new);
                        if (stringArray != null) {
                            l.setText("Professor ja existe");
                            return;
                        }
                        Users.add(new Professor(userToken,typeToken));
                        l.setText("Professor cadastrado com sucesso");
                    }
                    else if(Objects.equals(typeToken, "comunidade")) {
                        String[] stringArray = Users.stream()
                                .filter(user -> Objects.equals(user.getName(), userToken))
                                .toArray(String[]::new);
                        if (stringArray != null) {
                            l.setText("Comunidade ja existe");
                            return;
                        }
                        Users.add(new Comunidade(userToken,typeToken));
                        l.setText("Comunidade cadastrado com sucesso");
                    }
                });
            }
        );
        buttons[1].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

                l.setText("Digite o nome e o tipo do livro(texto,geral) a ser cadastrado separados por virgula");
                typingArea.setOnAction(event -> {

                    String bookName = typingArea.getText();
                    typingArea.setText(null);
                    System.out.println("String "+ bookName);
                    String[] token = bookName.split(",");

                    String noSpaceToken = token[1].replaceAll("\\s","");
                    String typeToken = noSpaceToken.toLowerCase();
                    String bookToken = token[0].toLowerCase();

                    if(Objects.equals(typeToken, "texto")) {
                        Books.add(new TextBook(bookToken,typeToken));
                        l.setText("Livro texto cadastrado com sucesso");
                    }
                    else if(Objects.equals(typeToken, "geral")) {
                        Books.add(new GeneralBook(bookToken,typeToken));
                        l.setText("Livro geral cadastrado com sucesso");
                    }
                });
            }
        );
        buttons[2].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

                l.setText("Digite o nome do usuario e do livro a ser pego emprestado separados por virgula sem espaco");

                typingArea.setOnAction(event -> {

                    String line = typingArea.getText();
                    typingArea.setText(null);
                    String[] token = line.split(",");

                    String userToken = token[0].toLowerCase();
                    String bookToken = token[1].toLowerCase();

                    for (User u : Users) {
                        if (Objects.equals(u.getName(), userToken)) {
                            for (Book b : Books) {
                                if(Objects.equals(b.getName(), bookToken)) {

                                    if(Objects.equals(b.getType(), "texto") && Objects.equals(u.getType(), "comunidade")) {
                                        l.setText("Usuario sem permissao para pegar esse livro");
                                        return;
                                    }
                                    else if(b.getStatus()) {
                                        l.setText("Livro nao disponivel");
                                        return;
                                    }
                                    else if(u.getTimeLimit() <= u.getBooks().size()) {
                                        l.setText("Usuario nao pode pegar mais livros");
                                    }
                                    else{
                                        u.getBooks().add(b);
                                        u.incCounter();
                                        b.setStatus(true);
                                        b.datetoSeconds(new Date());
                                        l.setText("Emprestimo feito com sucesso");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                });
            }
        );
        buttons[3].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                l.setText("Digite o nome do usuario e do livro a ser devolvido separados por virgula sem espaco");

                typingArea.setOnAction(event -> {

                    String line = typingArea.getText();
                    typingArea.setText(null);
                    String[] token = line.split(",");

                    String userToken = token[0].toLowerCase();
                    String bookToken = token[1].toLowerCase();

                    for (User u : Users) {
                        if (Objects.equals(u.getName(), userToken)) {
                            for (Book b : Books) {
                                if(Objects.equals(b.getName(), bookToken)) {

                                    u.getBooks().remove(b);
                                    u.decCounter();
                                    b.setStatus(false);
                                    return;
                                }
                            }
                        }
                    }
                });
            }
        );
        buttons[4].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Users
                .stream()
                .forEach(user -> area.appendText(user.getName() + ", " + user.getType() + "\n"))
        );
        buttons[5].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Books
                .stream()
                .forEach(book -> area.appendText(book.getName() +", "+ book.getType()+"\n"))
        );
        buttons[6].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Users
                .stream()
                        .filter(user -> user.getBooks().size() > 0)
                        .forEach (user -> {
                            area.appendText(user.getName());
                            for (int i = 0; i < user.bookCounter; i++) {
                                area.appendText(", " + user.getBooks().get(i).getName());
                            }
                            area.appendText("\n");
                        })
        );

        buttons[7].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                writeCsvFile("information.csv");
                Platform.exit();
            }
        );


        controlPanel.add(container,0,0);
        controlPanel.add(l,0,1);
        controlPanel.add(typingArea,0,2);
        controlPanel.add(area,0,3);

        Scene scene = new Scene(controlPanel, 700, 700);
        primaryStage.setTitle("Painel de Controle Biblioteca");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void readCsvFile(String fileName) {

        BufferedReader fileReader = null;
        try {

            String line;
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));

            fileReader.readLine();
            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    switch(tokens[1]) {
                        case "aluno":
                            Aluno a = new Aluno(tokens[0],tokens[1]);

                            for(int i=0 ; i < Integer.parseInt(tokens[2]); i ++ ) {
                                Book b = new Book(tokens[3*i+3],tokens[3*i+4]);
                                b.setBorrowedDate(Long.parseLong(tokens[3 * i + 5]));
                                b.setStatus(true);
                                a.getBooks().add(b);
                                a.incCounter();
                                Books.add(b);
                            }
                            Users.add(a);
                            break;

                        case "professor":
                            Professor p = new Professor(tokens[0],tokens[1]);

                            for(int i=0 ; i < Integer.parseInt(tokens[2]); i ++ ) {
                                Book b = new Book(tokens[3*i+3],tokens[3*i+4]);
                                b.setBorrowedDate(Long.parseLong(tokens[3 * i + 5]));
                                b.setStatus(true);
                                p.getBooks().add(b);
                                p.incCounter();
                                System.out.println("Livro add com sucesso");
                                Books.add(b);
                            }
                            Users.add(p);
                            break;

                        case "comunidade":
                            Comunidade c = new Comunidade(tokens[0],tokens[1]);

                            for(int i=0 ; i < Integer.parseInt(tokens[2]); i ++ ) {
                                Book b = new Book(tokens[3*i+3],tokens[3*i+4]);
                                b.setBorrowedDate(Long.parseLong(tokens[3 * i + 5]));
                                b.setStatus(true);
                                c.getBooks().add(b);
                                c.incCounter();
                                Books.add(b);
                            }
                            Users.add(c);
                            break;

                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }
    public static void writeCsvFile(String fileName) {


        FileWriter fileWriter = null;
        try {

            //Stream<String> lines = Files.lines(Paths.get(fileName));
            fileWriter = new FileWriter(fileName);
            fileWriter.append("nomeAluno, tipoAluno, numLivrosAlugados, nomeLivro, tipoLivro, dataLivro(em ms desde 1970)\n");
            //Write a new student object list to the CSV file
            /*
            switch(fileName) {
                case "users.csv":
                    for( User u : Users) {
                        fileWriter.append(u.getName());
                        fileWriter.append(",");
                        fileWriter.append(u.getType());
                        fileWriter.append("\n");
                    }
                    break;
                case "books.csv":
                    for( Book b : Books) {
                        fileWriter.append(b.getName());
                        fileWriter.append(",");
                        fileWriter.append(b.getType());
                        fileWriter.append("\n");
                    }
                    break;

                case "borrows.csv":
                */
                    for( User b : Users ) {
                        fileWriter.append(b.getName());
                        fileWriter.append(",");
                        fileWriter.append(b.getType());
                        fileWriter.append(",");
                        fileWriter.append(String.valueOf(b.getBooks().size()) );
                        for(Book b1 : b.getBooks() ) {

                            fileWriter.append(",");
                            fileWriter.append(b1.getName());
                            fileWriter.append(",");
                            fileWriter.append(b1.getType());
                            fileWriter.append(",");
                            fileWriter.append(String.valueOf(b1.getBorrowedDate()));
                        }
                        fileWriter.append("\n");
                    }
                    //break;
            //}

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String []args) {
        readCsvFile("information.csv");
        launch(args);
    }
}
