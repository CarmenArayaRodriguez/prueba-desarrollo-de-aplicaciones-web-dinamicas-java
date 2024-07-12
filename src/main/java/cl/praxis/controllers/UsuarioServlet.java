package cl.praxis.controllers;

import cl.praxis.models.Usuario;
import cl.praxis.models.dto.UsuarioDTO;
import cl.praxis.models.dao.UsuarioDAO;
import cl.praxis.models.dao.UsuarioDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            // Verifica que el usuario sea administrador
            if (usuario.getRolId() == 2) { 
                List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuariosConDirecciones();
                request.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("/view/usuarios.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/view/accesoDenegado.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
        }
    }
}
