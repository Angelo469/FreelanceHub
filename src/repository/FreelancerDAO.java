package repository;

import model.Freelancer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FreelancerDAO {

    public void inserir(Freelancer f) throws SQLException {
        String sql = "INSERT INTO freelancer (nome, especialidade, pais, taxa_por_hora) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getEspecialidade());
            stmt.setString(3, f.getPais());
            stmt.setDouble(4, f.getTaxaPorHora());
            stmt.executeUpdate();
        }
    }

    public List<Freelancer> listarTodos() throws SQLException {
        List<Freelancer> lista = new ArrayList<>();
        String sql = "SELECT * FROM freelancer";
        try (Connection conn = DataSourceConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Freelancer(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getString("pais"),
                        rs.getDouble("taxa_por_hora")
                ));
            }
        }
        return lista;
    }

    public void atualizar(Freelancer f) throws SQLException {
        String sql = "UPDATE freelancer SET nome=?, especialidade=?, pais=?, taxa_por_hora=? WHERE id=?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getEspecialidade());
            stmt.setString(3, f.getPais());
            stmt.setDouble(4, f.getTaxaPorHora());
            stmt.setInt(5, f.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM freelancer WHERE id=?";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
