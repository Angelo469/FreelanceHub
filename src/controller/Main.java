package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Objects.requireNonNull(
                            Main.class.getResource("/view/FreelancerView.fxml"),
                            "FreelancerView.fxml não encontrado no classpath"
                    )
            );
            Parent root = loader.load();

            primaryStage.setTitle("FreelanceHub - Gerenciamento de Projetos");
            primaryStage.setScene(new Scene(root, 900, 500));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar ProjetoView.fxml: " + e.getMessage());
        }
    }
}
