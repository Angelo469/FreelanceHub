package repository;
import java.sql.* ;
import model.Usuario;
public class UsuarioDAO {
    public Usuario findByEmail  (String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString   ("email"));
                usuario.setSenhaHash(rs.getString("senha_hash"));
                usuario.setRole(rs.getString("role"));
                return usuario;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}


