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
import java.util.stream.Collectors;


/**
 * Classe que representa o painel de controle da biblioteca, com todas as funções necessárias
 */
public class Library extends Application{

    // ArrayList de usuarios cadastrados
    protected static List<User> Users = new ArrayList<>();
    // Arraylist de livros cadastrados
    protected static List<Book> Books = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cria um gridPane para as opções
        GridPane controlPanel = new GridPane();
        controlPanel.setHgap(0);
        controlPanel.setVgap(0);
        controlPanel.setGridLinesVisible(true);

        // Cria um Vbox para conter os botoes
        VBox container = new VBox();
        container.setSpacing(10);
        container.setPadding(new Insets(10, 10, 10, 10));
        container.setPrefWidth(700);

        // Adiciona os botoes no Vbox
        Button buttons[] = new Button[8];
        for( int i = 0; i < 8; i ++) {
            buttons[i] = new Button();
            container.getChildren().add(buttons[i]);
            buttons[i].setMaxWidth(Double.MAX_VALUE);
        }

        // Cria um textArea e field para inputs e listagens
        TextArea area = new TextArea();
        TextField typingArea = new TextField();
        area.setEditable(false);
        Label l = new Label();

        // Nomeia os botoes
        buttons[0].setText("Cadastrar Usuario");
        buttons[1].setText("Cadastrar Livro");
        buttons[2].setText("Registrar Emprestimo");
        buttons[3].setText("Registrar Devolucao");
        buttons[4].setText("Listar Usuarios");
        buttons[5].setText("Listar Livros");
        buttons[6].setText("Listar Emprestimos");
        buttons[7].setText("Encerrar programa");

        // Evento acionado ao se clciar para cadastrar um usuario
        buttons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                // Apaga a area de texto
                area.setText("");


                l.setText("Digite o nome e o tipo(aluno,professor,comunidade) do usuario a ser cadastrado separados por virgula");
                // Adiciona evento no textfield
                typingArea.setOnAction(event -> {
                    // Recebe o que foi digitado
                    String name = typingArea.getText();
                    typingArea.setText(null);
                    // Separa o input em 2 ( nome do usuario e tipo )
                    String[] token = name.split(",");
                    // Remove os espaços e passa para minusculp
                    String noSpaceToken = token[1].replaceAll("\\s","");
                    String userToken = token[0].toLowerCase();
                    String typeToken = noSpaceToken.toLowerCase();

                    // Checa qual o tipo do usuario digitado
                    if(Objects.equals(typeToken, "aluno")) {
                        // Filtra todos os nomes iguais ao digitado
                        List<User>stringArray = Users.stream()
                                .filter(user -> Objects.equals(user.getName(), userToken))
                                .collect(Collectors.toList());
                        // Se ja existir aquele nome, avisa que ja existe e retorna. Dois nomes iguais nao sao suportados
                        if(!stringArray.isEmpty()) {
                            l.setText("Nome ja existe");
                            return;
                        }
                        // Se nao, adiciona um novo aluno com o nome e tipo
                        Users.add(new Aluno(userToken, typeToken));
                        l.setText("Aluno cadastrado com sucesso");
                    }
                    else if(Objects.equals(typeToken, "professor")) {
                        // A mesma coisa e feita com o tipo professor e comunidade
                        List<User>stringArray = Users.stream()
                                .filter(user -> Objects.equals(user.getName(), userToken))
                                .collect(Collectors.toList());
                        if(!stringArray.isEmpty()) {
                            l.setText("Nome ja existe");
                            return;
                        }
                        Users.add(new Aluno(userToken, typeToken));
                        l.setText("Professor cadastrado com sucesso");
                    }
                    else if(Objects.equals(typeToken, "comunidade")) {

                        List<User>stringArray = Users.stream()
                                .filter(user -> Objects.equals(user.getName(), userToken))
                                .collect(Collectors.toList());
                        if(!stringArray.isEmpty()) {
                            l.setText("Nome ja existe");
                            return;
                        }
                        Users.add(new Aluno(userToken, typeToken));
                        l.setText("COmunidade cadastrado com sucesso");
                    }
                });
            }
        );
        // Evento acionado ao se clicar para cadastrar um livro
        buttons[1].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                area.setText("");
                l.setText("Digite o nome e o tipo do livro(texto,geral) a ser cadastrado separados por virgula");
                typingArea.setOnAction(event -> {

                    // Recebe a string digitada no textField e a divide em nome e tipo do livro
                    String bookName = typingArea.getText();
                    typingArea.setText(null);
                    System.out.println("String "+ bookName);
                    //Separa o input em nome do livro e tipo
                    String[] token = bookName.split(",");
                    // Remove os espaços da string do tipo e passa para minusculo
                    String noSpaceToken = token[1].replaceAll("\\s","");
                    String typeToken = noSpaceToken.toLowerCase();
                    String bookToken = token[0].toLowerCase();

                    // Checa o tipo de livro digitado
                    // Se for texto,
                    if(Objects.equals(typeToken, "texto")) {
                        List<Book>stringArray = Books.stream()
                                .filter(book -> Objects.equals(book.getName(), bookToken))
                                .collect(Collectors.toList());
                        // Checa se ja existe algum livro com o mesmo nome.
                        if(!stringArray.isEmpty()) {
                            // Se sim, retorna
                            l.setText("Livro ja existe");
                            return;
                        }
                        // Se nao, adiciona um novo livro aos livros cadastrados
                        Books.add(new TextBook(bookToken,typeToken));
                        l.setText("Livro texto cadastrado com sucesso");
                    }
                    // O mesmo é feita para o caso do livro geral
                    else if(Objects.equals(typeToken, "geral")) {
                        List<Book>stringArray = Books.stream()
                                .filter(book -> Objects.equals(book.getName(), bookToken))
                                .collect(Collectors.toList());
                        if(!stringArray.isEmpty()) {
                            l.setText("Livro ja existe");
                            return;
                        }
                        Books.add(new GeneralBook(bookToken,typeToken));
                        l.setText("Livro geral cadastrado com sucesso");
                    }
                });
            }
        );
        // Evento acionado ao se clicar para realizar um emprestimo
        buttons[2].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                area.setText("");
                l.setText("Digite o nome do usuario e do livro a ser pego emprestado separados por virgula sem espaco");

                typingArea.setOnAction(event -> {

                    String line = typingArea.getText();
                    typingArea.setText(null);
                    String[] token = line.split(",");

                    String userToken = token[0].toLowerCase();
                    String bookToken = token[1].toLowerCase();

                    // Procura no vetor de usuarios pelo usuario com o nome digitado
                    for (User user : Users) {
                        if (Objects.equals(user.getName(), userToken)) {
                            // Se esse usuario esta suspenso, retorna
                            if (isSuspended(user)) {
                                l.setText("Usuario esta suspenso");
                                return;
                            }
                            // Se o usuario foi encontrado, procura agora pelo livro com o nome digitado
                            for (Book book : Books) {
                                if (Objects.equals(book.getName(), bookToken)) {
                                    // Se o livro é texto e o usuario é comunidade, retorna
                                    if (Objects.equals(book.getType(), "texto") && Objects.equals(user.getType(), "comunidade")) {
                                        l.setText("Usuario sem permissao para pegar esse livro");
                                        return;
                                        // Se o livro ja foi pego, retorna
                                    } else if (book.getStatus()) {
                                        l.setText("Livro nao disponivel");
                                        return;
                                        // Se o usuario ja esta com o limite de livros completo, retorna
                                    } else if (user.getTimeLimit() <= user.getBooks().size()) {
                                        l.setText("Usuario ja possui o limite de livros emprestados");
                                        return;
                                    } else {
                                        // Se nao, adiciona o livro a lista de livros que o usuario possui
                                        // e salva a data que o livro foi pego
                                        user.getBooks().add(book);
                                        user.incCounter();
                                        book.setStatus(true);
                                        book.setBorrowedDate(new Date().getTime() / 1000);
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
        // Evento acionado ao se clicar para realizar uma devolução
        buttons[3].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                area.setText("");
                l.setText("Digite o nome do usuario e do livro a ser devolvido separados por virgula sem espaco");

                typingArea.setOnAction(event -> {

                    String line = typingArea.getText();
                    typingArea.setText(null);
                    // Separa o input em nome do usuario e nome do livro
                    String[] token = line.split(",");
                    String userToken = token[0].toLowerCase();
                    String bookToken = token[1].toLowerCase();
                    // Procura na lista de usuarios pelo usuario com o nome digitado
                    for (User user : Users) {
                        if (Objects.equals(user.getName(), userToken)) {
                            // Procura na lista de livros pelo livro com nome digitado
                            for (Book book : Books) {
                                if(Objects.equals(book.getName(), bookToken)) {
                                    // Remove o livro da lista de livros que o usuario possui
                                    user.getBooks().remove(book);
                                    user.decCounter();
                                    book.setStatus(false);
                                    // Salva a data da devolucao que eh a data atual do sistema em s
                                    long CurrentDate = new Date().getTime()/1000;
                                    // Se essa data menos a data que o livro foi pego é mais que a data limite para se devolver o livro
                                    if (CurrentDate - book.getBorrowedDate() > user.getTimeLimit() ) {
                                        // O usuario esta suspenso
                                        user.setSuspendedDate(CurrentDate);
                                        // Salva a quantidade de tempo de suspensao
                                        user.setSuspensionTime(CurrentDate - user.getTimeLimit());
                                        user.setStatus(true);

                                        l.setText("Livro devolvido com sucesso mas usuario está suspenso por: " + user.getSuspensionTime() + " s");
                                        return;
                                    }
                                    l.setText("Livro devolvido com sucesso");
                                    return;
                                }
                            }
                        }
                    }
                });
            }
        );
        // Evento acionado ao se clicar para listar os usuarios
        // Transforma o vetor em uma stream e o imprime
        buttons[4].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Users
                .stream()
                .forEach(user -> area.appendText(user.getName() + ", " + user.getType() + "\n"))
        );
        // Evento acionaod ao se clicar para listar os livros
        // Transforma o vetor em uma stream e o imprime
        buttons[5].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Books
                .stream()
                .forEach(book -> area.appendText(book.getName() +", "+ book.getType()+"\n"))
        );
        // Evento acionado ao se clicar para listar os emprestimos
        // Transforma o vetor em uma stream, filtra para os usuarios que possuem algum livro emprestado,
        // e imprime cada usuario e cada livro que ele possui
        buttons[6].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    area.setText("");
                    Users
                            .stream()
                            .filter(user -> user.getBooks().size() > 0)
                            .forEach(user -> {
                                area.appendText(user.getName());
                                for (int i = 0; i < user.bookCounter; i++) {
                                    area.appendText(", " + user.getBooks().get(i).getName());
                                }
                                area.appendText("\n");
                            });
                }
        );
        // Evento acionado ao se clciar para encerrar o programa
        // Salva todas as informações necessarias num arquivo CSV e encerra a execução do programa
        buttons[7].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                writeCsvFile("information.csv");
                Platform.exit();
            }
        );

        // Adiciona todos os componentes ao gridpane base
        controlPanel.add(container,0,0);
        controlPanel.add(l,0,1);
        controlPanel.add(typingArea,0,2);
        controlPanel.add(area,0,3);

        // Inicializa a interface grafica
        Scene scene = new Scene(controlPanel, 700, 700);
        primaryStage.setTitle("Painel de Controle Biblioteca");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Le o arquivo no formato CSV e o divide em strings de acordo com cada tipo
     * @param fileName, nome do arquivo a ser lido
     */
    public void readCsvFile(String fileName) {

        BufferedReader fileReader = null;
        try {
            // String para armazenar uma linha lida
            String line;
            // Lista para armazenar os livros que ja estao emprestados
            List<Book>stringArray;
            //Cria um fileReadear
            fileReader = new BufferedReader(new FileReader(fileName));
            // Le a linha inicial que é o header
            fileReader.readLine();

            // Le o arquivo linha por linha a partir da segunda
            while ((line = fileReader.readLine()) != null) {
                //Pega todos os pedaços da linha separados por virgula
                String[] tokens = line.split(",");
                // Se tiver algo pra ler
                if (tokens.length > 0) {
                    // token[1] eh o tipo de usuario ou livro lido
                    switch(tokens[1]) {
                        case "aluno":
                            // Se leu aluno, adiciona um aluno ao vetor
                            Aluno a = new Aluno(tokens[0],tokens[1]);
                            // com as devidas informacoes sobre ele
                            receiveInfo(a,tokens);
                            break;
                            // O mesmo é feito com um professor e um comunidade
                        case "professor":
                            Professor p = new Professor(tokens[0],tokens[1]);
                            receiveInfo(p, tokens);
                            break;

                        case "comunidade":
                            Comunidade c = new Comunidade(tokens[0],tokens[1]);
                            receiveInfo(c, tokens);
                            break;
                        // Se o tipo lido for texto
                        case "texto":
                            // Se um livro com o nome lido nao existir na lista de emprestados de algum usuario lido antes
                            stringArray = Books.stream()
                                    .filter(book -> Objects.equals(book.getName(), tokens[0]))
                                    .collect(Collectors.toList());
                            // Adiciona esse livro no vetor de livros
                            if(stringArray.isEmpty()) {
                                Books.add(new TextBook(tokens[0],tokens[1]));
                            }
                            // O mesmo eh feito para o livro geral
                        case "geral":
                            stringArray = Books.stream()
                                    .filter(book -> Objects.equals(book.getName(), tokens[0]))
                                    .collect(Collectors.toList());
                            if(stringArray.isEmpty()) {
                                Books.add(new GeneralBook(tokens[0],tokens[1]));
                            }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            // Fecha o fileReader
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

    /**
     * Escreve no arquivo no formato CSV, respeitando as restricoes para esse formato
     * @param fileName, nome do arquivo onde sera escrito
     */
    public void writeCsvFile(String fileName) {

        FileWriter fileWriter = null;
        try {

            fileWriter = new FileWriter(fileName);
                // Imprime no arquivo o header de identificacao
            fileWriter.append("nomeAluno, tipoAluno, numLivrosAlugados, nomeLivro, tipoLivro, dataEmprestimo(em s desde 1970)\n");
            // Para cada usuario imprime todas as informacoes no arquivo de acordo com o header
            for( User user : Users ) {
                fileWriter.append(user.getName());
                fileWriter.append(",");
                fileWriter.append(user.getType());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(user.getBooks().size()) );
                for(Book book : user.getBooks() ) {

                    fileWriter.append(",");
                    fileWriter.append(book.getName());
                    fileWriter.append(",");
                    fileWriter.append(book.getType());
                    fileWriter.append(",");
                    fileWriter.append(String.valueOf(book.getBorrowedDate()));
                }
                fileWriter.append("\n");
            }
            // Apos ter imprimido todos os usuarios, imprime todos os livros cadastrados
            fileWriter.append("nomeLivro, tipoLivro\n");
                for( Book book : Books) {
                    fileWriter.append(book.getName());
                    fileWriter.append(",");
                    fileWriter.append(book.getType());
                    fileWriter.append("\n");
                }

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

    /**
     *
     * @param U Um usuario
     * @param tokens o vetor de strings lido do arquivo
     */
    public void receiveInfo(User U, String[] tokens) {
        // token[2] representa a quantidade de livros que o usuario tem alugado
        for(int i=0 ; i < Integer.parseInt(tokens[2]); i ++ ) {
            // a partir dai, no arquivo, tem-se o nome do livro, o tipo e a data em ms do aluguel
            // de cada livro. Logo, para isso ser lido num loop, é ligo token de 3i+3, 3i+4 e 3i + 5 pra
            // cada uma dessas infnormacoes
            Book b = new Book(tokens[((3 * i) + 3)],tokens[((3 * i) + 4)]);
            b.setBorrowedDate(Long.parseLong(tokens[((3 * i) + 5)]));
            b.setStatus(true);
            U.getBooks().add(b);
            U.incCounter();
            // Adiciona o novo livro no vetor
            Books.add(b);
        }
        // Adiciona o novo usuario tambem
        Users.add(U);
    }

    /**
     *
     * @param U um objeto do tipo usuario
     * @return true se a condicao é satisfeita e false se nao é
     */
    public boolean isSuspended(User U) {
        // Recebe a data atual do sistema
        long CurrentDate = new Date().getTime()/1000;
        return CurrentDate - U.getSuspendedDate() <= U.getSuspensionTime();
    }
}
