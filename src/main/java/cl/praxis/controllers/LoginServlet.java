package cl.praxis.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cl.praxis.models.Usuario;
import cl.praxis.models.dto.UsuarioDTO;
import cl.praxis.models.dao.UsuarioDAO;
import cl.praxis.models.dao.UsuarioDAOImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDAO.findByCorreo(correo);

        if (usuario != null && usuario.getPassword().equals(password)) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNombre(), usuario.getCorreo(), usuario.getPassword(), usuario.getNick(), usuario.getPeso(), usuario.getDireccionNombre(), usuario.getDireccionNumeracion(), usuario.getRolId());
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioDTO);

            // Verifica el objeto almacenado en la sesión
            System.out.println("Usuario almacenado en sesión: " + usuarioDTO.getNombre() + ", Rol: " + usuarioDTO.getRolId());

            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Usuario no encontrado o contraseña incorrecta, redirigir a la página de login con mensaje de error
            response.sendRedirect(request.getContextPath() + "/view/login.jsp?error=true");
        }
    }
}
