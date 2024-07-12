package cl.praxis.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.praxis.models.dto.UsuarioDTO;
import cl.praxis.models.services.UsuarioService;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    private UsuarioService usuarioService = new UsuarioService();

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Inicializando RegistroServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 request.setCharacterEncoding("UTF-8");
    	 response.setCharacterEncoding("UTF-8");
    	    
        System.out.println("Procesando solicitud POST en RegistroServlet");

        String nombre = request.getParameter("nombre");
        String nick = request.getParameter("nick");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        // Captura y convierte el peso 
        int peso = 0;
        String pesoStr = request.getParameter("peso");
        if (pesoStr != null && !pesoStr.isEmpty()) {
            try {
                peso = Integer.parseInt(pesoStr);
                System.out.println("Peso recibido: " + peso);
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir el peso: " + e.getMessage());
                // Asigna un valor por defecto o manejar el error adecuadamente
                peso = 0; // valor por defecto si hay error
            }
        }

        // Captura y convierte la numeración de la dirección de forma segura
        int direccionNumeracion = 0;
        String direccionNumeracionStr = request.getParameter("direccion_numeracion");
        if (direccionNumeracionStr != null && !direccionNumeracionStr.isEmpty()) {
            try {
                direccionNumeracion = Integer.parseInt(direccionNumeracionStr);
                System.out.println("Dirección Numeración recibida: " + direccionNumeracion);
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir la numeración de la dirección: " + e.getMessage());
                // Asigna un valor por defecto o manejar el error adecuadamente
                direccionNumeracion = 0; // valor por defecto si hay error
            }
        }

        String direccionNombre = request.getParameter("direccion_nombre");
        int rolId = Integer.parseInt(request.getParameter("rol_id"));

        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre, correo, password, nick, peso, direccionNombre, direccionNumeracion, rolId);

        try {
            usuarioService.registrar(usuarioDTO);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioDTO);
            System.out.println("Registro exitoso. Usuario " + nombre + " registrado.");
            response.sendRedirect(request.getContextPath() + "/view/registroExitoso.jsp");
        } catch (IllegalStateException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/view/registro.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 request.setCharacterEncoding("UTF-8"); 
    	 response.setCharacterEncoding("UTF-8");
        System.out.println("Procesando solicitud GET en RegistroServlet");
        request.getRequestDispatcher("/view/registro.jsp").forward(request, response);
    }
}
