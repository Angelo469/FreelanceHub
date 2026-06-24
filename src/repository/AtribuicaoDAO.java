package repository;

import model.Atribuicao;
import model.Freelancer;
import model.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtribuicaoDAO {

    public void inserir(Atribuicao a) throws SQLException {
        String sql = "INSERT INTO atribuicao (freelancer_id, projeto_id, horas_trabalhadas) VALUES (?, ?, ?)";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, a.getFreelancer().getId());
            stmt.setInt(2, a.getProjeto().getId());
            stmt.setInt(3, a.getHorasTrabalhadas());
            stmt.executeUpdate();
        }
    }

    public List<Atribuicao> listarTodos() throws SQLException {
        List<Atribuicao> lista = new ArrayList<>();
        String sql = """
                SELECT a.id, a.horas_trabalhadas,
                       f.id as f_id, f.nome, f.especialidade, f.pais, f.taxa_por_hora,
                       p.id as p_id, p.nome as p_nome, p.cliente, p.prazo, p.valor_dolar, p.status, p.freelancer_id
                FROM atribuicao a
                JOIN freelancer f ON a.freelancer_id = f.id
                JOIN projeto p ON a.projeto_id = p.id
                """;
        try (Connection conn = DataSourceConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Freelancer f = new Freelancer(rs.getInt("f_id"), rs.getString("nome"),
                        rs.getString("especialidade"), rs.getString("pais"), rs.getDouble("taxa_por_hora"));
                Projeto p = new Projeto(rs.getInt("p_id"), rs.getString("p_nome"),
                        rs.getString("cliente"), rs.getDate("prazo").toLocalDate(),
                        rs.getDouble("valor_dolar"), Projeto.Status.valueOf(rs.getString("status")),
                        rs.getInt("freelancer_id")); // Adicionado freelancer_id
                lista.add(new Atribuicao(rs.getInt("id"), f, p, rs.getInt("horas_trabalhadas")));
            }
        }
        return lista;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM atribuicao WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}