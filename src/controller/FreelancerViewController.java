package controller;

import controller.FreelancerController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Freelancer;
import java.sql.SQLException;

public class FreelancerViewController {

    private final FreelancerController controller = new FreelancerController();

    @FXML private TextField nomeField;
    @FXML private TextField especialidadeField;
    @FXML private TextField paisField;
    @FXML private TextField taxaField;

    @FXML private TableView<Freelancer> tabelaFreelancer;
    @FXML private TableColumn<Freelancer, String> colId;
    @FXML private TableColumn<Freelancer, String> colNome;
    @FXML private TableColumn<Freelancer, String> colEspecialidade;
    @FXML private TableColumn<Freelancer, String> colPais;
    @FXML private TableColumn<Freelancer, String> colTaxa;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));
        colEspecialidade.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEspecialidade()));
        colPais.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPais()));
        colTaxa.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getTaxaPorHora())));

        carregarTabela();

        tabelaFreelancer.getSelectionModel().selectedItemProperty().addListener((obs, old, novo) -> {
            if (novo != null) {
                nomeField.setText(novo.getNome());
                especialidadeField.setText(novo.getEspecialidade());
                paisField.setText(novo.getPais());
                taxaField.setText(String.valueOf(novo.getTaxaPorHora()));
            }
        });
    }

    @FXML
    public void cadastrar() throws SQLException {
        if (!validarCampos()) {
            return;
        }

        controller.cadastrar(
                nomeField.getText(),
                especialidadeField.getText(),
                paisField.getText(),
                Double.parseDouble(taxaField.getText())
        );
        carregarTabela();
        limparCampos();
    }

    @FXML
    public void atualizar() throws SQLException {
        Freelancer selecionado = tabelaFreelancer.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            controller.atualizar(new Freelancer(
                    selecionado.getId(),
                    nomeField.getText(),
                    especialidadeField.getText(),
                    paisField.getText(),
                    Double.parseDouble(taxaField.getText())
            ));
            carregarTabela();
            limparCampos();
        }
    }

    @FXML
    public void deletar() throws SQLException {
        Freelancer selectedFreelancer = tabelaFreelancer.getSelectionModel().getSelectedItem();

        if (selectedFreelancer != null) {
            controller.deletar(selectedFreelancer.getId());
            carregarTabela();
        }
    }

    private void carregarTabela() {
        try {
            tabelaFreelancer.getItems().clear();
            tabelaFreelancer.getItems().addAll(controller.listarTodos());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        nomeField.clear();
        especialidadeField.clear();
        paisField.clear();
        taxaField.clear();
    }

    private boolean validarCampos() {
        if (nomeField.getText().isEmpty() ||
                especialidadeField.getText().isEmpty() ||
                paisField.getText().isEmpty() ||
                taxaField.getText().isEmpty()) {
            mostrarAlerta("Campos vazios", "Preencha todos os campos!");
            return false;
        }

        try {
            Double.parseDouble(taxaField.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Taxa inválida", "Digite apenas números na taxa por hora!");
            return false;
        }

        return true;
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
