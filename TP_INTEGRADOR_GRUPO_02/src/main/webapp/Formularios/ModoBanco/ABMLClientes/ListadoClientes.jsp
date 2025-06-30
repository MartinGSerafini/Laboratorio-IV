<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Cliente"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Listado de Clientes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoBancoCSS/ABMLClientesCSS/ListadoClientes.css?v=2">
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-custom px-4">
    <div class="container-fluid">
      <div class="d-flex align-items-center">
        <div class="dropdown me-3">
          <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">☰</button>
          <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="${pageContext.request.contextPath}/AgregarClienteServlet">CLIENTES</a></li>
    		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/AgregarCuentaServlet">CUENTAS</a></li>
    		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/AutorizacionPrestamosServlet">PRÉSTAMOS</a></li>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/InformesServlet">INFORMES</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/Formularios/Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a>
			</ul>
        </div>
        <a href="${pageContext.request.contextPath}/AgregarClienteServlet" class="btn btn-custom me-2">Agregar Cliente</a>
        <a href="${pageContext.request.contextPath}/ListadoClientesServlet" class="btn btn-custom">Listar Clientes</a>
      </div>
      <span class="navbar-text text-white">
        <%= session.getAttribute("adminLogueado") != null ? session.getAttribute("adminLogueado") : "INVITADO" %>
      </span>
    </div>
  </nav>

  <div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Listado de Clientes</h3>

    <div id="mensajeError" class="alert alert-warning d-none text-center" role="alert">
      Por favor, seleccione una columna para aplicar el filtro.
    </div>

    <form class="d-flex justify-content-center mb-4" method="get" action="/TP_INTEGRADOR_GRUPO_02/ListadoClientesServlet" onsubmit="return validarFiltro()">
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
          <%= col.replace("_cliente", "").toUpperCase() %>
        </option>
        <% }
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
      ArrayList<Cliente> lista = null;
      if(request.getAttribute("ListaCli") != null){
          lista = (ArrayList<Cliente>) request.getAttribute("ListaCli");
      }
      int paginaActual = (request.getAttribute("paginaActual") != null) ? (Integer)request.getAttribute("paginaActual") : 1;
      int totalPaginas = (request.getAttribute("totalPaginas") != null) ? (Integer)request.getAttribute("totalPaginas") : 1;
    %>

    <div class="table-responsive" style="width: 100vw; margin-left: calc(-50vw + 50%); padding: 0 12px;">
   <table class="table table-striped table-bordered text-center align-middle" style="font-size: 0.85rem;">
        <thead class="table-dark">
          <tr>
            <th>ID Cliente</th>
            <th>DNI</th>
            <th>CUIL</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Sexo</th>
            <th>Nacionalidad</th>
            <th>Nacimiento</th>
            <th>Dirección</th>
            <th>Provincia</th>
            <th>Localidad</th>
            <th>Correo</th>
            <th>Teléfono</th>
            <th>Usuario</th>
            <th>Contraseña</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <% if(lista != null)
            for(Cliente cli : lista) { %>
            <tr data-id='<%= cli.getIdCliente() %>'>
              <td data-campo="id_cliente"><%= cli.getIdCliente() %></td>
              <td data-campo="dni_cliente"><%= cli.getDniCliente() %></td>
              <td data-campo="cuil_cliente"><%= cli.getCuilCliente() %></td>
              <td data-campo="nombre_cliente"><%= cli.getNombreCliente() %></td>
              <td data-campo="apellido_cliente"><%= cli.getApellidoCliente() %></td>
              <td data-campo="sexo_cliente"><%= cli.getSexoCliente() %></td>
              <td data-campo="nacionalidad_cliente"><%= cli.getNombreNacionalidad() %></td>
              <td data-campo="fechaNac_cliente"><%= cli.getFechaNacCliente() %></td>
              <td data-campo="direccion_cliente"><%= cli.getDireccionCliente() %></td>
              <td data-campo="provincia_cliente"><%= cli.getNombreProvincia() %></td>
              <td data-campo="localidad_cliente"><%= cli.getNombreLocalidad() %></td>
              <td data-campo="correo_cliente"><%= cli.getCorreoCliente() %></td>
              <td data-campo="telefono_cliente"><%= cli.getTelefonoCliente() %></td>
              <td data-campo="usuario_Cliente"><%= cli.getUsuarioCliente() %></td>
              <td data-campo="contraseña_cliente"><%= cli.getContrasenaCliente() %></td>
              <td>
                <button class="btn btn-warning btn-sm" onclick="editarFila(this)">Editar</button>
                <button class="btn btn-success btn-sm d-none" onclick="guardarCambios(this)">Guardar</button>
                <button class="btn btn-secondary btn-sm d-none" onclick="cancelarCambios(this)">Cancelar</button>
                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#confirmarEliminarModal" data-id="<%= cli.getIdCliente() %>">Eliminar</button>
              </td>
            </tr>
          <% } %>
        </tbody>
      </table>
    </div>

    <nav class="d-flex justify-content-center mt-4">
      <ul class="pagination">
        <li class="page-item <%= (paginaActual == 1) ? "disabled" : "" %>"><a class="page-link" href="ListadoClientesServlet?pagina=<%= paginaActual - 1 %>">Anterior</a></li>
        <% for(int i = 1; i <= totalPaginas; i++) { %>
          <li class="page-item <%= (i == paginaActual) ? "active" : "" %>"><a class="page-link" href="ListadoClientesServlet?pagina=<%= i %>"><%= i %></a></li>
        <% } %>
        <li class="page-item <%= (paginaActual == totalPaginas) ? "disabled" : "" %>"><a class="page-link" href="ListadoClientesServlet?pagina=<%= paginaActual + 1 %>">Siguiente</a></li>
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
          <div class="modal-body"> ¿Estás seguro que deseas eliminar este cliente? </div>
          <div class="modal-footer">
            <form id="formEliminar" method="post" action="/TP_INTEGRADOR_GRUPO_02/EliminarClienteServlet">
              <input type="hidden" name="idCliente" id="idClienteEliminar">
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
            ¿Estás seguro que deseas modificar este cliente?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-warning" id="confirmarGuardarBtn">Sí, modificar</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
          </div>
        </div>
      </div>
    </div>

  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

  <script>
    const listaNacionalidades = [
      <% 
        ArrayList<entidades.Nacionalidad> listaNac = (ArrayList<entidades.Nacionalidad>) request.getAttribute("listaNacionalidades");
        if (listaNac != null) {
          for (int i = 0; i < listaNac.size(); i++) {
            entidades.Nacionalidad n = listaNac.get(i);
            out.print("{ id:" + n.getId() + ", nombre:'" + n.getNacionalidad().replace("'", "\\'") + "'}");
            if (i < listaNac.size() - 1) out.print(",");
          }
        }
      %>
    ];
    const listaProvincias = [
      <% 
        ArrayList<entidades.Provincia> listaProv = (ArrayList<entidades.Provincia>) request.getAttribute("listaProvincias");
        if (listaProv != null) {
          for (int i = 0; i < listaProv.size(); i++) {
            entidades.Provincia p = listaProv.get(i);
            out.print("{ id:" + p.getId() + ", nombre:'" + p.getProvincia().replace("'", "\\'") + "' }");
            if (i < listaProv.size() - 1) out.print(",");
          }
        }
      %>
    ];
    const listaLocalidades = [
      <% 
        ArrayList<entidades.Localidad> listaLoc = (ArrayList<entidades.Localidad>) request.getAttribute("listaLocalidades");
        if (listaLoc != null) {
          for (int i = 0; i < listaLoc.size(); i++) {
            entidades.Localidad l = listaLoc.get(i);
            out.print("{ id:" + l.getId() + ", nombre:'" + l.getLocalidad().replace("'", "\\'") + "', idProvincia:" + l.getId_pcia() + " }");
            if (i < listaLoc.size() - 1) out.print(",");
          }
        }
      %>
    ];

    function escapeHtml(text) {
      const map = {
        '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#039;'
      };
      return text.replace(/[&<>"']/g, m => map[m]);
    }

    async function cargarLocalidadesPorProvincia(idProvincia, localidadActual) {
      try {
        const url = '/TP_INTEGRADOR_GRUPO_02/LocalidadesPorProvinciaServlet?idProvincia=' + encodeURIComponent(idProvincia);
        const res = await fetch(url);
        if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);
        const localidades = await res.json();

        const select = document.createElement('select');
        select.className = 'form-select form-select-sm';

        localidades.forEach(loc => {
          const option = document.createElement('option');
          option.value = loc.id;
          option.textContent = loc.nombre;
          if (String(loc.id) === String(localidadActual)) option.selected = true; 
          select.appendChild(option);
        });

        return select;
      } catch (error) {
        console.error('Error cargando localidades:', error);
        const select = document.createElement('select');
        select.className = 'form-select form-select-sm';
        select.innerHTML = '<option>Error al cargar localidades</option>';
        return select;
      }
    }

    async function editarFila(btnEditar) {
      const fila = btnEditar.closest('tr');
      const tds = fila.querySelectorAll('td[data-campo]');

      for (const td of tds) {
        const valor = td.textContent.trim();
        td.setAttribute('data-valor-original', valor);
        const campo = td.getAttribute('data-campo');

        if (campo === 'id_cliente') {
          td.innerText = valor;

        } else if (campo === 'sexo_cliente') {
          td.innerHTML =
            '<select class="form-select form-select-sm">' +
            '<option value="Masculino"' + (valor === 'Masculino' ? ' selected' : '') + '>Masculino</option>' +
            '<option value="Femenino"' + (valor === 'Femenino' ? ' selected' : '') + '>Femenino</option>' +
            '</select>';

        } else if (campo === 'provincia_cliente') {
          const select = document.createElement('select');
          select.className = 'form-select form-select-sm';
          listaProvincias.forEach(p => {
            const option = document.createElement('option');
            option.value = p.id;
            option.textContent = p.nombre;
            if (String(p.id) === String(valor) || p.nombre === valor) option.selected = true;
            select.appendChild(option);
          });

          select.addEventListener('change', async (e) => {
            const provinciaId = e.target.value;
            const provinciaObj = listaProvincias.find(p => p.id == provinciaId);
            if (!provinciaObj) return;
            const fila = e.target.closest('tr');
            const tdLocalidad = fila.querySelector('td[data-campo="localidad_cliente"]');
            if (!tdLocalidad) return;

            tdLocalidad.innerHTML = '';
            const selectLocalidad = await cargarLocalidadesPorProvincia(provinciaObj.id, null);
            tdLocalidad.appendChild(selectLocalidad);
          });

          td.innerHTML = '';
          td.appendChild(select);

        } else if (campo === 'localidad_cliente') {
        	  const tdProvincia = fila.querySelector('td[data-campo="provincia_cliente"]');
        	  const selectProvincia = tdProvincia ? tdProvincia.querySelector('select') : null;
        	  const provinciaActualId = selectProvincia ? selectProvincia.value : null;

        	  const localidadActualId = td.getAttribute('data-id-original') || valor;

        	  td.innerHTML = '';

        	  if (provinciaActualId) {
        	    const provinciaObj = listaProvincias.find(p => p.id == provinciaActualId);
        	    if (provinciaObj) {
        	      const selectLocalidad = await cargarLocalidadesPorProvincia(provinciaObj.id, localidadActualId);
        	      td.appendChild(selectLocalidad);
        	    } else {
        	      td.innerHTML = '<span>Provincia no encontrada</span>';
        	    }
        	  } else {
        	    td.innerHTML = '<span>Seleccione provincia primero</span>';
        	  }
        } else if (campo === 'nacionalidad_cliente') {
          const select = document.createElement('select');
          select.className = 'form-select form-select-sm';
          listaNacionalidades.forEach(n => {
            const option = document.createElement('option');
            option.value = n.id;
            option.textContent = n.nombre;
            if (n.nombre === valor || String(n.id) === valor) option.selected = true;
            select.appendChild(option);
          });
          td.innerHTML = '';
          td.appendChild(select);

        } else if (campo === 'fechaNac_cliente') {
          const input = document.createElement('input');
          input.type = 'date';
          input.className = 'form-control form-control-sm';
          input.value = valor;
          const hoy = new Date().toISOString().split('T')[0];
          input.max = hoy;
          td.innerHTML = '';
          td.appendChild(input);

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
      const idCliente = filaEnEdicion.getAttribute('data-id');
      const tds = filaEnEdicion.querySelectorAll('td[data-campo]');
      datosAEnviar = new URLSearchParams();

      datosAEnviar.append("idCliente", idCliente);

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
      fetch('/TP_INTEGRADOR_GRUPO_02/ModificarClienteServlet', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
        body: datosAEnviar.toString()
      })
      .then(res => res.json())
      .then(resp => {
        if (resp.success) {
          alert('✔️ Cliente modificado correctamente');
          toggleBotones(filaEnEdicion, false);

          filaEnEdicion.querySelectorAll('td[data-campo]').forEach(td => {
        	  const campo = td.getAttribute('data-campo');
        	  const valorId = datosAEnviar.get(campo);
        	  td.setAttribute('data-valor-original', valorId);

        	  if (campo === "provincia_cliente") {
        	    const provincia = listaProvincias.find(p => String(p.id) === valorId);
        	    td.textContent = provincia ? provincia.nombre : valorId;

        	  } else if (campo === "nacionalidad_cliente") {
        	    const nacionalidad = listaNacionalidades.find(n => String(n.id) === valorId);
        	    td.textContent = nacionalidad ? nacionalidad.nombre : valorId;

        	  } else if (campo === "localidad_cliente") {
            	  console.log("localidad_cliente valorId:", valorId);
            	  console.log("listaLocalidades:", listaLocalidades);

        		  const localidad = listaLocalidades.find(l => String(l.id) === valorId);
        		  td.textContent = localidad ? localidad.nombre : valorId;
        		  td.setAttribute('data-id-original', valorId);

        	  } else {
        	    td.textContent = valorId;
        	  }
        	});

        } else {
          alert("❌ " + resp.mensaje);
        }
      })
      .catch(err => {
        console.error("Error en fetch:", err);
        alert("❌ Error al intentar guardar los cambios: " + err.message);
      });

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
      const idCliente = boton.getAttribute('data-id');
      document.getElementById('idClienteEliminar').value = idCliente;
    });
  </script>
</body>
</html>
