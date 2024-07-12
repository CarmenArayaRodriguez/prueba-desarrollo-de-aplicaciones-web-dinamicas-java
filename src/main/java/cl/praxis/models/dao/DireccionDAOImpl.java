package cl.praxis.models.dao;

import cl.praxis.models.Direccion;
import cl.praxis.models.conexion.BDConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DireccionDAOImpl implements DireccionDAO {
    private Connection connection;

    public DireccionDAOImpl() {
        this.connection = BDConexion.getConnection();
    }

    @Override
    public void save(Direccion direccion) {
        String query = "INSERT INTO direcciones (nombre, numeracion, usuario_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, direccion.getNombre());
            stmt.setInt(2, direccion.getNumeracion());
            stmt.setInt(3, direccion.getUsuarioId());
            stmt.executeUpdate();
            System.out.println("Dirección guardada exitosamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar dirección: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
