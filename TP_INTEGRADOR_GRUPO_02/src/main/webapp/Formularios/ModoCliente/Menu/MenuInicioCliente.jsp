<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Inicio</title>
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoClienteCSS/Menu/MenuInicioCliente.css">
    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById("sidebar");
            sidebar.style.display = sidebar.style.display === "block" ? "none" : "block";
        }
    </script>
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-custom px-4">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="dropdown me-3">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">
                    ☰
                </button>      
                <ul class="dropdown-menu">
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/InfoPersonalClienteServlet">Informacion Personal</a></li>
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a></li>
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/TransferenciasClienteServlet">Transferir Dinero</a></li>
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/NuevoPrestamoServlet">Nuevo Prestamo</a></li>
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PagarPrestamoServlet">Pagar Prestamo</a></li>
					 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Formularios/Login/InicioUsuario.jsp">Cerrar Sesion</a></li>
				</ul>
            </div>
        </div>
        <span class="navbar-text text-white">
        <%= session.getAttribute("ClienteLogueado") != null ? session.getAttribute("ClienteLogueado") : "INVITADO" %>
      </span>
    </div>
</nav>

<div class="main-content">
    <h2>Menú Inicio</h2>
    <div class="button-grid">
    	<a href="${pageContext.request.contextPath}/InfoPersonalClienteServlet" class="btn big-btn">INFORMACION PERSONAL</a>
        <a href="${pageContext.request.contextPath}/CuentasClienteServlet" class="btn big-btn">CUENTAS</a>
        <a href="${pageContext.request.contextPath}/TransferenciasClienteServlet" class="btn big-btn">TRASFERIR DINERO</a>
        <a href="${pageContext.request.contextPath}/NuevoPrestamoServlet" class="btn big-btn">NUEVO PRESTAMO</a>
        <a href="${pageContext.request.contextPath}/PagarPrestamoServlet" class="btn big-btn">PAGAR PRESTAMOS</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
