<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Administrador - Inicio de Sesión</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/LoginCSS/IngresoAdministrador.css">
  
  
</head>

<body>

<div class="login-card text-center position-relative">
  <a href="InicioUsuario.jsp" class="back-arrow">
  	<i class="bi bi-arrow-left"></i> Volver
  </a>
  <div class="admin-box">Administrador</div>
  <br><h4 class="login-title">Inicio de Sesión</h4>

  <form method="post" action="${pageContext.request.contextPath}/loginAdministradorServlet" class="text-start">
    <div class="mb-3">
      <label for="usuario" class="form-label">Usuario</label>
      <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Ingrese su usuario">
    </div>
    <div class="mb-3">
      <label for="contraseña" class="form-label">Contraseña</label>
      <input type="password" class="form-control" id="contrasena" name="contrasena" placeholder="Ingrese su contraseña">
    </div>

   <% if (request.getAttribute("errorLogin") != null) { %>
    	<small class="text-danger">Usuario o contraseña incorrectos.</small>
   <% } %>

    <div class="d-grid mt-4">
      <button type="submit" class="btn btn-custom btn-lg">Aceptar</button>
    </div>

  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
