package cl.praxis.models.services;

import cl.praxis.models.Usuario;
import cl.praxis.models.dao.UsuarioDAO;
import cl.praxis.models.dao.UsuarioDAOImpl;
import cl.praxis.models.dto.UsuarioDTO;

import cl.praxis.models.dao.DireccionDAO;
import cl.praxis.models.dao.DireccionDAOImpl;
import cl.praxis.models.Direccion;
import cl.praxis.models.dao.RolUsuarioDAO;
import cl.praxis.models.dao.RolUsuarioDAOImpl;
import cl.praxis.models.RolUsuario;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private DireccionDAO direccionDAO = new DireccionDAOImpl();
    private RolUsuarioDAO rolUsuarioDAO = new RolUsuarioDAOImpl();

    public UsuarioDTO login(String correo, String password) {
        Usuario usuario = usuarioDAO.findByCorreo(correo);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return new UsuarioDTO(usuario.getNombre(), usuario.getCorreo(), usuario.getPassword(), usuario.getNick(), usuario.getPeso(), usuario.getDireccionNombre(), usuario.getDireccionNumeracion(), usuario.getRolId());
        }
        return null;
    }

    public void registrar(UsuarioDTO usuarioDTO) {
        Usuario existente = usuarioDAO.findByCorreo(usuarioDTO.getCorreo());
        if (existente == null) {
            Usuario nuevoUsuario = new Usuario(usuarioDTO.getNombre(), usuarioDTO.getCorreo(), usuarioDTO.getPassword(), usuarioDTO.getNick(), usuarioDTO.getPeso(), usuarioDTO.getDireccionNombre(), usuarioDTO.getDireccionNumeracion(), usuarioDTO.getRolId());
            usuarioDAO.save(nuevoUsuario);

            Direccion direccion = new Direccion(usuarioDTO.getDireccionNombre(), usuarioDTO.getDireccionNumeracion(), nuevoUsuario.getId());
            direccionDAO.save(direccion);

            RolUsuario rolUsuario = new RolUsuario(nuevoUsuario.getId(), usuarioDTO.getRolId());
            rolUsuarioDAO.save(rolUsuario);

        } else {
            throw new IllegalStateException("Usuario ya registrado con este correo");
        }
    }
}
