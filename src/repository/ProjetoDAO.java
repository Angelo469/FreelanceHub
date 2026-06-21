package repository;

import model.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    public void inserir(Projeto p) throws SQLException {
        String sql = "INSERT INTO projeto (nome, cliente, prazo, valor_dolar, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCliente());
            stmt.setDate(3, Date.valueOf(p.getPrazo()));
            stmt.setDouble(4, p.getValorDolar());
            stmt.setString(5, p.getStatus().name());
            stmt.executeUpdate();
        }
    }

    public List<Projeto> listarTodos() throws SQLException {
        List<Projeto> lista = new ArrayList<>();
        String sql = "SELECT * FROM projeto";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Projeto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cliente"),
                        rs.getDate("prazo").toLocalDate(),
                        rs.getDouble("valor_dolar"),
                        Projeto.Status.valueOf(rs.getString("status"))
                ));
            }
        }
        return lista;
    }

    public void atualizar(Projeto p) throws SQLException {
        String sql = "UPDATE projeto SET nome=?, cliente=?, prazo=?, valor_dolar=?, status=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCliente());
            stmt.setDate(3, Date.valueOf(p.getPrazo()));
            stmt.setDouble(4, p.getValorDolar());
            stmt.setString(5, p.getStatus().name());
            stmt.setInt(6, p.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM projeto WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
