<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-3">Registro de usuario</h1>
        <form action="${pageContext.request.contextPath}/RegistroServlet" method="post" class="needs-validation" novalidate>
            <div class="form-group">
                Nombre: <input type="text" name="nombre" class="form-control" required />
            </div>
            <div class="form-group">
                Nick: <input type="text" name="nick" class="form-control" required />
            </div>
            <div class="form-group">
                Correo: <input type="email" name="correo" class="form-control" required />
            </div>
            <div class="form-group">
                Contraseña: <input type="password" name="password" class="form-control" required />
            </div>
            <div class="form-group">
                Peso: <input type="number" name="peso" class="form-control" min="0" />
            </div>
            <div class="form-group">
                Calle: <input type="text" name="direccion_nombre" class="form-control" />
            </div>
            <div class="form-group">
                Número: <input type="number" name="direccion_numeracion" class="form-control" min="1" />
            </div>
            <div class="form-group">
                Rol: <select name="rol_id" class="form-control">
                	<option value="">Seleccione un rol</option>
                    <option value="1">Usuario</option>
                    <option value="2">Administrador</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>
    </div>
</body>
</html>
