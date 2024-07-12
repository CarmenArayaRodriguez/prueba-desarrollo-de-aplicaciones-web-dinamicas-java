package cl.praxis.models.dao;

import cl.praxis.models.Usuario;
import java.util.List;

public interface UsuarioDAO {
    Usuario findByCorreo(String correo);
    void save(Usuario usuario);
    List<Usuario> obtenerTodosLosUsuariosConDirecciones();
}
