<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Inicio usuario</title>
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
</head>

<body class="bg-light">

<div class="container text-center mt-5">
  <div class="card p-4 shadow mx-auto" style="max-width: 400px;">
    <h4 class="mb-3">Ingrese su usuario y contraseña</h4>

    <div class="container mt-4">
      <form method="post">
        <div class="form-group">
          <label for="usuario" class="text-left d-block">Usuario</label>
          <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Ingrese su usuario aquí">
        </div>
        <div class="form-group">
          <label for="contraseña" class="text-left d-block">Contraseña</label>
          <input type="text" class="form-control" id="contraseña" name="contraseña" placeholder="Ingrese su contraseña aquí">
        </div>

        <% if ("POST".equalsIgnoreCase(request.getMethod())) { %>
          <div class="text-danger mb-3">Usuario o contraseña incorrectos.</div>
        <% } %>

        <div class="d-grid gap-3">
          <button type="submit" class="btn btn-primary btn-lg">Aceptar</button>
        </div>
        
        <div class="text-center mt-3">
         <p><a href="registro.jsp"> ¿No tienes cuenta? crea una aquí</a></p>
        </div>
        <div class="text-center mt-3">
         <p> <a href="registro.jsp">¿No recuerdas tu contraseña?</a></p>
        </div>
        <div class="text-center mt-3">
         <p> <a href="registro.jsp">¿Necesitas ayuda?</a></p>
        </div>
        
      </form>
    </div>

  </div>
</div>

</body>
</html>
