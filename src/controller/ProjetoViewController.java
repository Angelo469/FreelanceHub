package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import model.Projeto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProjetoViewController {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FreelancerController freelancerController = new FreelancerController();
    private final ProjetoController controller = new ProjetoController();

    @FXML private TextField nomeField;
    @FXML private TextField clienteField;
    @FXML private DatePicker prazoField;
    @FXML private TextField valorField;
    @FXML private ComboBox<String> statusCombo;
    @FXML private ComboBox<model.Freelancer> freelancerCombo;
    @FXML private TableView<Projeto> tabelaProjeto;
    @FXML private TableColumn<Projeto, String> colId;
    @FXML private TableColumn<Projeto, String> colNome;
    @FXML private TableColumn<Projeto, String> colCliente;
    @FXML private TableColumn<Projeto, String> colPrazo;
    @FXML private TableColumn<Projeto, String> colValor;
    @FXML private TableColumn<Projeto, String> colStatus;

    @FXML
    public void initialize() {
        statusCombo.getItems().addAll("EM_ANDAMENTO", "CONCLUIDO", "ATRASADO");
        configurarDatePicker();

        // Carregar freelancers no ComboBox
        try {
            freelancerCombo.getItems().setAll(freelancerController.listarTodos());
            freelancerCombo.setConverter(new StringConverter<model.Freelancer>() {
                @Override
                public String toString(model.Freelancer freelancer) {
                    return freelancer != null ? freelancer.getNome() : "";
                }

                @Override
                public model.Freelancer fromString(String string) {
                    return null;
                }
            });
        } catch (SQLException e) {
            mostrarAlerta("Erro ao carregar freelancers", "Nao foi possivel carregar a lista de freelancers: " + e.getMessage());
        }

        configurarColunas();
        carregarTabela();

        tabelaProjeto.getSelectionModel().selectedItemProperty().addListener((obs, old, novo) -> {

            if (novo != null) {
                nomeField.setText(novo.getNome());
                clienteField.setText(novo.getCliente());
                prazoField.setValue(novo.getPrazo());
                valorField.setText(String.format("%.2f", novo.getValorDolar()));
                statusCombo.setValue(novo.getStatus().name());
               
                // Preencher o ComboBox do freelancer
                if (novo.getFreelancerId() != 0) { // Corrigido: Adicionado ')'
                    freelancerCombo.getItems().stream() // Corrigido: 'steam()' para 'stream()'
                            .filter(f -> f.getId() == novo.getFreelancerId())
                            .findFirst() // Corrigido: 'filterFirst()' para 'findFirst()'
                            .ifPresent(freelancerCombo::setValue);
                } else {
                    freelancerCombo.setValue(null); // Limpa a seleção se não houver freelancer
                }
            }
        });
    }

    private void configurarDatePicker() {
        prazoField.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return date != null ? FORMATO_DATA.format(date) : "";
            }

            @Override
            public LocalDate fromString(String value) {
                if (value == null || value.isBlank()) {
                    return null;
                }
                try {
                    return LocalDate.parse(value.trim(), FORMATO_DATA);
                } catch (DateTimeParseException e) {
                    mostrarAlerta("Data invalida", "Use o formato dd/MM/aaaa (ex: 31/12/2026).");
                    return null;
                }
            }
        });
    }

    private void configurarColunas() {
        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colNome.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));
        colCliente.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getCliente()));
        colPrazo.setCellValueFactory(data -> {
            LocalDate prazo = data.getValue().getPrazo();
            String texto = prazo != null ? FORMATO_DATA.format(prazo) : "";
            return new javafx.beans.property.SimpleStringProperty(texto);
        });
        colValor.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        String.format("US$ %.2f", data.getValue().getValorDolar())));
        colStatus.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(formatarStatus(data.getValue().getStatus())));
    }

    private String formatarStatus(Projeto.Status status) {
        return switch (status) {
            case EM_ANDAMENTO -> "Em andamento";
            case CONCLUIDO -> "Concluido";
            case ATRASADO -> "Atrasado";
        };
    }

    @FXML
    public void cadastrar() {
        if (!validarCampos()) {
            return;
        }

        try {
            // Pega o freelancer selecionado no ComboBox
            model.Freelancer freelancerSelecionado = freelancerCombo.getValue();
            // Se nenhum freelancer for selecionado, usa 0 ou outro valor que indique "nenhum"
            int freelancerId = (freelancerSelecionado != null) ? freelancerSelecionado.getId() : 0; 

            controller.cadastrar(
                    nomeField.getText().trim(),
                    clienteField.getText().trim(),
                    prazoField.getValue(),
                    Double.parseDouble(valorField.getText().trim().replace(",", ".")),
                    Projeto.Status.valueOf(statusCombo.getValue()),
                    freelancerId // Passa o ID do freelancer
            );

            carregarTabela();
            limparCampos();
            mostrarSucesso("Projeto cadastrado com sucesso!");
        } catch (SQLException e) {
            mostrarAlerta("Erro no banco", "Nao foi possivel cadastrar: " + e.getMessage());
        }
    }

    @FXML
    public void atualizar() {
        Projeto selecionado = tabelaProjeto.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta("Nenhum projeto", "Selecione um projeto na tabela para atualizar.");
            return;
        }
        if (!validarCampos()) {
            return;
        }

        try {
            // Pega o freelancer selecionado no ComboBox
            model.Freelancer freelancerSelecionado = freelancerCombo.getValue();
            // Se nenhum freelancer for selecionado, usa 0 ou outro valor que indique "nenhum"
            int freelancerId = (freelancerSelecionado != null) ? freelancerSelecionado.getId() : 0; 

            controller.atualizar(new Projeto(
                    selecionado.getId(),
                    nomeField.getText().trim(),
                    clienteField.getText().trim(),
                    prazoField.getValue(),
                    Double.parseDouble(valorField.getText().trim().replace(",", ".")),
                    Projeto.Status.valueOf(statusCombo.getValue()),
                    freelancerId // Passa o ID do freelancer
            ));
            carregarTabela();
            limparCampos();
            mostrarSucesso("Projeto atualizado com sucesso!");
        } catch (SQLException e) {
            mostrarAlerta("Erro no banco", "Nao foi possivel atualizar: " + e.getMessage());
        }
    }

    @FXML
    public void deletar() {
        Projeto selecionado = tabelaProjeto.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta("Nenhum projeto", "Selecione um projeto na tabela para deletar.");
            return;
        }

        try {
            controller.deletar(selecionado.getId());
            carregarTabela();
            limparCampos();
            mostrarSucesso("Projeto deletado com sucesso!");
        } catch (SQLException e) {
            mostrarAlerta("Erro no banco", "Nao foi possivel deletar: " + e.getMessage());
        }
    }

    private void carregarTabela() {
        try {
            tabelaProjeto.getItems().setAll(controller.listarTodos());
        } catch (SQLException e) {
            mostrarAlerta(
                    "Erro de conexao",
                    "Nao foi possivel conectar ao MySQL.\n\n"
                            + "Verifique se o MySQL esta rodando e se o banco 'freelancehub' existe.\n\n"
                            + "Detalhe: " + e.getMessage()
            );
        }
    }

    private void limparCampos() {
        nomeField.clear();
        clienteField.clear();
        prazoField.setValue(null);
        valorField.clear();
        statusCombo.setValue(null);
        freelancerCombo.setValue(null); // Adicionado para limpar o ComboBox do freelancer
        tabelaProjeto.getSelectionModel().clearSelection();
    }

    private boolean validarCampos() {
        if (nomeField.getText().trim().isEmpty()
                || clienteField.getText().trim().isEmpty()
                || prazoField.getValue() == null
                || valorField.getText().trim().isEmpty()
                || statusCombo.getValue() == null
                || freelancerCombo.getValue() == null) { // Adicionada validação para o freelancer
            mostrarAlerta("Campos vazios", "Preencha todos os campos, incluindo o prazo, o status e o freelancer responsável!");
            return false;
        }

        try {
            Double.parseDouble(valorField.getText().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            mostrarAlerta("Valor invalido", "Digite apenas numeros no valor (USD)!");
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

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}