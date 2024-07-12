<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Acceso Denegado</title>
</head>
<body>
    <h1>Acceso Denegado</h1>
    <p>No tienes permisos para acceder a esta página.</p>
    <a href="<%= request.getContextPath() %>/view/login.jsp">Volver al Login</a>
</body>
</html>
