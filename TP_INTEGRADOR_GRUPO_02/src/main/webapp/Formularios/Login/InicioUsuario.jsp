<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Inicio Usuario</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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

    .card-custom {
      background-color: white;
      padding: 2.5rem;
      border-radius: 20px;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
      max-width: 420px;
      width: 100%;
      text-align: center;
    }

    h1, h2 {
      color: #ec0000;
      font-weight: bold;
    }

    h4 {
      font-weight: 600;
      margin-bottom: 1.5rem;
    }

    .btn-custom {
      font-weight: bold;
      font-size: 1.1rem;
      padding: 0.75rem;
      border: 2px solid #ec0000;
      transition: 0.3s;
    }

    .btn-cliente {
      background-color: white;
      color: #ec0000;
    }

    .btn-cliente:hover {
      background-color: #ec0000;
      color: white;
    }

    .btn-admin {
      background-color: #ec0000;
      color: white;
    }

    .btn-admin:hover {
      background-color: white;
      color: #ec0000;
    }
  </style>
</head>
<body>

  <div class="card-custom">
    <h1>Sistema de Gestión</h1>
    <h2>Banco BG2</h2>
    <h4>Seleccionar Usuario</h4>

    <div class="d-grid gap-3 mt-4">
      <a href="IngresoCliente.jsp" class="btn btn-custom btn-cliente">Cliente</a>
      <a href="admin.jsp" class="btn btn-custom btn-admin">Administrador</a>
    </div>
  </div>

</body>
</html>
