package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import at.favre.lib.crypto.bcrypt.BCrypt;
import model.Usuario;
import repository.UsuarioDAO;
import util.SessionManager;
import java.io.IOException;

public class LoginViewController {
    
    @FXML private TextField emailField;
    @FXML private PasswordField senhaField;
    @FXML private Button loginButton;
    @FXML private Label erroLabel;
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    @FXML
    public void initialize() {
        loginButton.setOnAction(e -> fazerLogin());
    }
    
    private void fazerLogin() {
        String email = emailField.getText();
        String senha = senhaField.getText();
        
        Usuario usuario = usuarioDAO.findByEmail(email);
        
        if (usuario != null) {
            if (BCrypt.verifyer().verify(senha.toCharArray(), usuario.getSenhaHash()).verified) {
                SessionManager.setUsuario(usuario);
                
                loginButton.setText("Carregando...");
                loginButton.setDisable(true);
                
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FreelancerView.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(new Scene(root, 900, 700)); // Aumentei a altura para 700
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                erroLabel.setText("Senha incorreta!");
            }
        } else {
            erroLabel.setText("Email não encontrado!");
        }
    }
}