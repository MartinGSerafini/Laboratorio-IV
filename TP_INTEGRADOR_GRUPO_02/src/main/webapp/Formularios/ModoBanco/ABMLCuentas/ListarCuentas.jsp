<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="entidades.TipoCuenta"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Listado de Clientes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ABMLClientesCSS/ListarCuentas.css">
</head>
<body>
  <!-- Barra de Navegación -->
  <nav class="navbar navbar-expand-lg navbar-custom px-4">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="dropdown me-3">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">☰</button>
                <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="${pageContext.request.contextPath}/AgregarClienteServlet">CLIENTES</a></li>
    		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/AgregarCuentaServlet">CUENTAS</a></li>
    		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/AutorizacionPrestamosServlet">PRÉSTAMOS</a></li>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}//Formularios/ModoBanco/Informes/Informes.jsp">INFORMES</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/Formularios/Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a>
			</ul>
            </div>
            <a href="${pageContext.request.contextPath}/AgregarCuentasServlet" class="btn btn-custom me-2">Agregar Cuenta</a>
            <a href="${pageContext.request.contextPath}/ListadoCuentasServlet" class="btn btn-custom">Listar Cuentas</a>
        </div>
        <span class="navbar-text text-white">
            <%= session.getAttribute("adminLogueado") != null ? session.getAttribute("adminLogueado") : "INVITADO" %>        
        </span>
    </div>
  </nav>

  <div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Listado de Cuentas</h3>

    <div id="mensajeError" class="alert alert-warning d-none text-center" role="alert">
      Por favor, seleccione una columna para aplicar el filtro.
    </div>

    <!-- Busqueda y Filtros -->
    <form class="d-flex justify-content-center mb-4" method="get"
          action="/TP_INTEGRADOR_GRUPO_02/ListadoCuentasServlet"
          onsubmit="return validarFiltro()">
      <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
      <select name="filtro" class="form-select w-25 me-2" id="filtroSelect">
        <option value="">Seleccione una columna</option>
        <%
          ArrayList<String> columnas = (ArrayList<String>) request.getAttribute("columnas");
          String filtroSeleccionado = (String) request.getAttribute("filtroSeleccionado"); 
          if (columnas != null) {
              for (String col : columnas) {
        %>
        <option value="<%= col %>" <%= col.equals(filtroSeleccionado) ? "selected" : "" %>>
            <%= col.replace("_cuenta", "").toUpperCase() %>
        </option>
        <%  }
          }
        %>
      </select>
      <button type="submit" class="btn btn-custom" name="btnbuscar">Buscar</button>
    </form>

    <script>
      function validarFiltro() {
        const filtro = document.getElementById('filtroSelect').value;
        const mensajeError = document.getElementById('mensajeError');
        if (!filtro) {
          mensajeError.classList.remove('d-none');
          return false;
        } else {
          mensajeError.classList.add('d-none');
          return true;
        }
      }
    </script>
    
<%
  ArrayList<Cuenta> lista = null;
  if(request.getAttribute("ListaCue") != null){
      lista = (ArrayList<Cuenta>) request.getAttribute("ListaCue");
  }

  int paginaActual = (request.getAttribute("paginaActual") != null) ? (Integer)request.getAttribute("paginaActual") : 1;
  int totalPaginas = (request.getAttribute("totalPaginas") != null) ? (Integer)request.getAttribute("totalPaginas") : 1;
%>

  <!-- Tabla -->
<div class="table-responsive">
  <table class="table table-striped table-bordered text-center">
    <thead class="table-dark">
      <tr>
        <th style="min-width: 120px; white-space: nowrap;">ID Cuenta</th>
        <th>ID CLiente</th>
        <th>Fecha Creación</th>
        <th>Número de Cuenta</th>
        <th>CBU</th>
        <th>Saldo</th>
        <th>Tipo de Cuenta</th>
        <th>Acciones</th>
      </tr>
    </thead>
    <tbody>
      <% if(lista != null)
          for(Cuenta cue : lista) { %>
      <tr data-id='<%= cue.getIdCuenta() %>'>
        <td data-campo="id_cuenta"><%= cue.getIdCuenta() %></td>
        <td data-campo="idCliente_cuenta"><%= cue.getIdClienteCuenta() %></td>
        <td data-campo="fechaCreacion_cuenta"><%= cue.getFechaCreacionCuenta() %></td>
        <td data-campo="numero_cuenta"><%= cue.getNumeroCuenta() %></td>
        <td data-campo="cbu_cuenta"><%= cue.getCbuCuenta() %></td>
        <td data-campo="saldo_cuenta"><%= cue.getSaldoCuenta() %></td>
        <td data-campo="idTipoCuenta" data-id="<%= cue.getIdTipoCuentaCuenta() %>">
            <%= cue.getIdTipoCuentaCuenta() %> - <%= cue.getTipoCuentaCuenta() %></td>
        <td>
          <button class="btn btn-warning btn-sm" onclick="editarFila(this)">Editar</button>
          <button class="btn btn-success btn-sm d-none" onclick="guardarCambios(this)">Guardar</button>
          <button class="btn btn-secondary btn-sm d-none" onclick="cancelarCambios(this)">Cancelar</button>
          <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#confirmarEliminarModal" data-id="<%= cue.getIdCuenta() %>">Eliminar</button>
        </td>
      </tr>
      <% } %>
    </tbody>
  </table>
</div>

<!-- Paginación -->
<nav class="d-flex justify-content-center mt-4">
  <ul class="pagination">
    <li class="page-item <%= (paginaActual == 1) ? "disabled" : "" %>">
      <a class="page-link" href="ListadoCuentasServlet?pagina=<%= paginaActual - 1 %>">Anterior</a>
    </li>
    <% for(int i = 1; i <= totalPaginas; i++) { %>
      <li class="page-item <%= (i == paginaActual) ? "active" : "" %>">
        <a class="page-link" href="ListadoCuentasServlet?pagina=<%= i %>"><%= i %></a>
      </li>
    <% } %>
    <li class="page-item <%= (paginaActual == totalPaginas) ? "disabled" : "" %>">
      <a class="page-link" href="ListadoCuentasServlet?pagina=<%= paginaActual + 1 %>">Siguiente</a>
    </li>
  </ul>
</nav>

 <!-- Modal Confirmar Eliminación -->
    <div class="modal fade" id="confirmarEliminarModal" tabindex="-1" aria-labelledby="confirmarEliminarLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-danger text-white">
            <h5 class="modal-title">Confirmar Eliminación</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body"> ¿Estás seguro que deseas eliminar esta cuenta? </div>
          <div class="modal-footer">
            <form id="formEliminar" method="post" action="/TP_INTEGRADOR_GRUPO_02/EliminarCuentaServlet">
              <input type="hidden" name="idCuenta" id="idCuentaEliminar">
              <button type="submit" class="btn btn-danger">Sí, eliminar</button>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Modal Confirmar Modificación -->
    <div class="modal fade" id="confirmarModificarModal" tabindex="-1" aria-labelledby="confirmarModificarLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-warning">
            <h5 class="modal-title">Confirmar Modificación</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body">
            ¿Estás seguro que deseas modificar esta cuenta?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-warning" id="confirmarGuardarBtn">Sí, modificar</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
          </div>
        </div>
      </div>
    </div>
    
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<script>
  function escapeHtml(text) {
    const map = {
      '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#039;'
    };
    return text.replace(/[&<>"']/g, m => map[m]);
  }

  async function editarFila(btnEditar) {
    const fila = btnEditar.closest('tr');
    const tds = fila.querySelectorAll('td[data-campo]');

    for (const td of tds) {
      const valor = td.textContent.trim();
      td.setAttribute('data-valor-original', valor);
      const campo = td.getAttribute('data-campo');

      if (campo === 'id_cuenta') {
        td.innerText = valor;

      }  else if (campo === 'idTipoCuenta') {
    	  const idTipo = td.getAttribute("data-id");

    	  td.innerHTML =
    	    '<select class="form-select form-select-sm">' +
    	    '<option value="1"' + (idTipo === '1' ? ' selected' : '') + '>Caja de Ahorro</option>' +
    	    '<option value="2"' + (idTipo === '2' ? ' selected' : '') + '>Cuenta Corriente</option>' +
    	    '</select>';
    	    
    	} else {
        td.innerHTML = '<input type="text" class="form-control form-control-sm" value="' + escapeHtml(valor) + '">';
      }
    }

    toggleBotones(fila, true);
  }

  // Variables globales para manejar datos y fila en edición
  let datosAEnviar = null;
  let filaEnEdicion = null;

  function guardarCambios(btnGuardar) {
    filaEnEdicion = btnGuardar.closest('tr');
    const idCuenta = filaEnEdicion.getAttribute('data-id');
    const tds = filaEnEdicion.querySelectorAll('td[data-campo]');
    datosAEnviar = new URLSearchParams();

    datosAEnviar.append("idCuenta", idCuenta);

    tds.forEach(td => {
      const campo = td.getAttribute('data-campo');
      const input = td.querySelector('input');
      const select = td.querySelector('select');
      let valor = "";

      if (input) valor = input.value.trim();
      else if (select) {
        // En select puede estar un option seleccionado
        if (select.options) valor = select.options[select.selectedIndex].value;
        else valor = select.value.trim();
      }
      else valor = td.textContent.trim();

      datosAEnviar.append(campo, valor);
    });

    // Mostrar modal para confirmar modificación
    const modalModificar = new bootstrap.Modal(document.getElementById('confirmarModificarModal'));
    modalModificar.show();
  }

  document.getElementById('confirmarGuardarBtn').addEventListener('click', () => {
	    fetch('/TP_INTEGRADOR_GRUPO_02/ModificarCuentaServlet', {
	      method: 'POST',
	      headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
	      body: datosAEnviar.toString()
	    })
	    .then(res => res.json())
	    .then(resp => {
	  if (resp.success) {
	    alert('✔️ Cuenta modificada correctamente');
	    filaEnEdicion.querySelectorAll('td[data-campo]').forEach(td => {
	      const campo = td.getAttribute('data-campo');
	      const input = td.querySelector('input');
	      const select = td.querySelector('select');
	      const nuevoValor = datosAEnviar.get(campo);

	      td.setAttribute('data-valor-original', nuevoValor);

	      if (input || select) {
	        if (campo === "idTipoCuenta") {
	          let texto = "";
	          switch (nuevoValor) {
	            case "1":
	              texto = "1 - Caja de Ahorro";
	              break;
	            case "2":
	              texto = "2 - Cuenta Corriente";
	              break;
	            default:
	              texto = nuevoValor;
	          }
	          td.textContent = texto;
	        } else {
	          td.textContent = nuevoValor;
	        }
	      }
	    });

	    toggleBotones(filaEnEdicion, false);
	  } else {
	    alert("❌ " + resp.mensaje);
	  }
	})

    // Ocultar modal
    const modalEl = document.getElementById('confirmarModificarModal');
    const modalInstance = bootstrap.Modal.getInstance(modalEl);
    modalInstance.hide();
  });

  function cancelarCambios(btnCancelar) {
    const fila = btnCancelar.closest('tr');
    fila.querySelectorAll('td[data-campo]').forEach(td => {
      const original = td.getAttribute('data-valor-original');
      td.textContent = original;
    });
    toggleBotones(fila, false);
  }

  function toggleBotones(fila, editar) {
    fila.querySelectorAll('button').forEach(btn => {
      if (btn.textContent.trim() === "Editar") btn.classList.toggle('d-none', editar);
      else if (btn.textContent.trim() === "Guardar") btn.classList.toggle('d-none', !editar);
      else if (btn.textContent.trim() === "Cancelar") btn.classList.toggle('d-none', !editar);
      else if (btn.classList.contains('btn-danger')) btn.disabled = editar;
    });
  }

// Modal eliminar: asignar idCliente al input hidden
const modalEliminar = document.getElementById('confirmarEliminarModal');
modalEliminar.addEventListener('show.bs.modal', function (event) {
  const boton = event.relatedTarget;
  const idCuenta = boton.getAttribute('data-id');
  document.getElementById('idCuentaEliminar').value = idCuenta;
});
</script>

</body>
</html>