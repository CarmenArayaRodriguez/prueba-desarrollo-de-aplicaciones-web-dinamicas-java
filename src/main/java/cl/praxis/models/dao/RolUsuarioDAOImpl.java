package cl.praxis.models.dao;

import cl.praxis.models.RolUsuario;
import cl.praxis.models.conexion.BDConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RolUsuarioDAOImpl implements RolUsuarioDAO {
    private Connection connection;

    public RolUsuarioDAOImpl() {
        this.connection = BDConexion.getConnection();
    }

    @Override
    public void save(RolUsuario rolUsuario) {
        String query = "INSERT INTO roles_usuarios (usuario_id, rol_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rolUsuario.getUsuarioId());
            stmt.setInt(2, rolUsuario.getRolId());
            stmt.executeUpdate();
            System.out.println("Rol de usuario guardado exitosamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar rol de usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
