package Biblioteca;

import javafx.application.Application;
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


public class Library extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane controlPanel = new GridPane();
        controlPanel.setHgap(0);
        controlPanel.setVgap(0);
        controlPanel.setGridLinesVisible(true);

        VBox container = new VBox();
        container.setSpacing(10);
        container.setPadding(new Insets(0, 20, 10, 20));

        Button buttons[] = new Button[7];
        for( int i = 0; i < 7; i ++) {
            buttons[i] = new Button();
            addEventOnButton(buttons[i]);
            container.getChildren().add(buttons[i]);
            buttons[i].setMaxWidth(Double.MAX_VALUE);
        }
        buttons[0].setText("Cadastrar Usuario");
        buttons[1].setText("Cadastrar Livro");
        buttons[2].setText("Registrar Emprestimo");
        buttons[3].setText("Registrar Devolucao");
        buttons[4].setText("Listar Usuarios");
        buttons[5].setText("Listar Livros");
        buttons[6].setText("Listar Emprestimos");

        TextArea area = new TextArea();
        TextField typingArea = new TextField();
        typingArea.setEditable(false);
        Label l = new Label();

        controlPanel.add(container,0,0);
        controlPanel.add(l,0,1);
        controlPanel.add(typingArea,0,2);
        controlPanel.add(area,0,3);

        Scene scene = new Scene(controlPanel, 600, 600);
        primaryStage.setTitle("Painel de Controle Biblioteca");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void addEventOnButton( Button b) {
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

                }
        );
    }
    public static void main(String []args) {
        launch(args);
    }
}
