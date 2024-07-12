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

import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Obtiene la sesión sin crear una nueva
        HttpSession session = request.getSession(false);
        if (session != null) {
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            if (usuario != null) {
            	// Verifica que el usuario sea administrador
                if (usuario.getRolId() == 2) {  
                    List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuariosConDirecciones();
                    request.setAttribute("usuarios", usuarios);
                    request.getRequestDispatcher("/view/home.jsp").forward(request, response);
                } else {
                    // Redirige a una página de acceso denegado si el usuario no es administrador
                    response.sendRedirect(request.getContextPath() + "/view/accesoDenegado.jsp");
                }
            } else {
                // Redirige a login si no hay usuario en la sesión
                response.sendRedirect(request.getContextPath() + "/view/login.jsp");
            }
        } else {
            // Redirige a login si no hay sesión
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
        }
    }
}
