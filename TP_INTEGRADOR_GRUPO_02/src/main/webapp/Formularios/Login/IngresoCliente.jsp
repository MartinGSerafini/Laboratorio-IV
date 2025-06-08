<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inicio de Sesión</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Montserrat', sans-serif;
      background: linear-gradient(135deg, #ffffff 0%, #ffe6eb 100%);
      height: 100vh;
      margin: 0;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .login-card {
      background-color: white;
      padding: 2.5rem;
      border-radius: 20px;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
      max-width: 420px;
      width: 100%;
    }

    .form-label {
      font-weight: 600;
    }

    .btn-custom {
      background-color: white;
      color: #ec0000;
      border: 2px solid #ec0000;
      font-weight: bold;
      transition: 0.3s;
    }

    .btn-custom:hover {
      background-color: #ec0000;
      color: white;
    }

    .login-title {
      color: #ec0000;
      font-weight: bold;
      margin-bottom: 1.5rem;
    }

    .link-custom {
      color: #ec0000;
      font-weight: 500;
      text-decoration: none;
    }

    .link-custom:hover {
      text-decoration: underline;
    }

    .error-message {
      color: #dc3545;
      font-weight: bold;
      margin-top: 1rem;
    }
  </style>
</head>

<body>

<div class="login-card text-center">
  <h4 class="login-title">Inicio de Sesión</h4>

  <form method="post" class="text-start">
    <div class="mb-3">
      <label for="usuario" class="form-label">Usuario</label>
      <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Ingrese su usuario">
    </div>
    <div class="mb-3">
      <label for="contraseña" class="form-label">Contraseña</label>
      <input type="password" class="form-control" id="contraseña" name="contraseña" placeholder="Ingrese su contraseña">
    </div>

    <% if ("POST".equalsIgnoreCase(request.getMethod())) { %>
      <div class="error-message text-center">Usuario o contraseña incorrectos.</div>
    <% } %>

    <div class="d-grid mt-4">
      <button type="submit" class="btn btn-custom btn-lg">Aceptar</button>
    </div>

    <div class="text-center mt-4">
      <a href="registro.jsp" class="link-custom d-block mb-2">¿No tienes cuenta? Crear una aquí</a>
      <a href="registro.jsp" class="link-custom d-block mb-2">¿No recuerdas tu contraseña?</a>
      <a href="registro.jsp" class="link-custom d-block">¿Necesitas ayuda?</a>
    </div>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
