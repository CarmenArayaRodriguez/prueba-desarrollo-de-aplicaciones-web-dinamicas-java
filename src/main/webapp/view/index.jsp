<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>StartUp - Bienvenido</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-3">Bienvenido a StartUp</h1>
        <div class="d-flex">
            <form action="${pageContext.request.contextPath}/view/registro.jsp" method="get" class="mr-2">
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </form>
            <form action="${pageContext.request.contextPath}/view/login.jsp" method="post">
                <button type="submit" class="btn btn-secondary">Login</button>
            </form>
        </div>
    </div>
</body>
</html>
