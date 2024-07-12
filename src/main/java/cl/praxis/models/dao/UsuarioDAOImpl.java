package cl.praxis.models.dao;

import cl.praxis.models.Usuario;
import cl.praxis.models.conexion.BDConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection connection;

    public UsuarioDAOImpl() {
        this.connection = BDConexion.getConnection();
    }

    @Override
    public Usuario findByCorreo(String correo) {
        String query = "SELECT u.*, d.nombre AS direccion_nombre, d.numeracion AS direccion_numeracion, ru.rol_id " +
                       "FROM usuarios u " +
                       "LEFT JOIN direcciones d ON u.id = d.usuario_id " +
                       "LEFT JOIN roles_usuarios ru ON u.id = ru.usuario_id " +
                       "WHERE u.correo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("password"),
                        rs.getString("nick"),
                        rs.getInt("peso"),
                        rs.getString("direccion_nombre"),
                        rs.getInt("direccion_numeracion"),
                        rs.getInt("rol_id")
                    );
                    usuario.setId(rs.getInt("id"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Usuario usuario) {
        String query = "INSERT INTO usuarios (nombre, correo, password, nick, peso) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getPassword());
            stmt.setString(4, usuario.getNick());
            stmt.setInt(5, usuario.getPeso());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                }
            }
            System.out.println("Usuario guardado exitosamente en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuariosConDirecciones() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT u.*, d.nombre AS direccion_nombre, d.numeracion AS direccion_numeracion, ru.rol_id " +
                       "FROM usuarios u " +
                       "LEFT JOIN direcciones d ON u.id = d.usuario_id " +
                       "LEFT JOIN roles_usuarios ru ON u.id = ru.usuario_id";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("password"),
                    rs.getString("nick"),
                    rs.getInt("peso"),
                    rs.getString("direccion_nombre"),
                    rs.getInt("direccion_numeracion"),
                    rs.getInt("rol_id")
                );
                usuario.setId(rs.getInt("id"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
