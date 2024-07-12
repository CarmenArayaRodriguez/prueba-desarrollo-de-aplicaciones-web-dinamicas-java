<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="cl.praxis.models.Usuario" %>
<%@ page import="cl.praxis.models.dto.UsuarioDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bienvenido</title>
    <!-- Incluir Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h2>Bienvenido</h2>
                    </div>
                    <div class="card-body">
                        <%
                            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
                            if (usuario != null) {
                        %>
                            <p>Has accedido al sistema con el correo <strong><%= usuario.getCorreo() %></strong>.</p>
                            
                            <% if (usuario.getRolId() == 2) { %>
                                <h4>Sección de Administrador</h4>
                                <p>Contenido exclusivo para administradores.</p>
                                <h5>Lista de Usuarios</h5>
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Correo</th>
                                            <th>Dirección</th>
                                            <th>Numeración</th>
                                            <th>Rol ID</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                                            if (usuarios != null) {
                                                for (Usuario user : usuarios) {
                                        %>
                                        <tr>
                                            <td><%= user.getId() %></td>
                                            <td><%= user.getNombre() %></td>
                                            <td><%= user.getCorreo() %></td>
                                            <td><%= user.getDireccionNombre() %></td>
                                            <td><%= user.getDireccionNumeracion() %></td>
                                            <td><%= user.getRolId() %></td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
                            <% } else { %>
                                <h4>Sección de Usuario Regular</h4>
                                <p>Contenido disponible para todos los usuarios.</p>
                            <% } %>
                        <%
                            } else {
                                response.sendRedirect(request.getContextPath() + "/view/login.jsp");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

