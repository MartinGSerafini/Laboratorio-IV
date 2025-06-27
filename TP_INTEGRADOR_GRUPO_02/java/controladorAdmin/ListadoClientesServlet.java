package controladorAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Nacionalidad;
import negocio.NegocioCliente;
import negocio.NegocioNacionalidad;

@WebServlet("/ListadoClientesServlet")
public class ListadoClientesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NegocioCliente negocio = new NegocioCliente();

        ArrayList<String> columnas = negocio.obtenerColumnasClientes();
        request.setAttribute("columnas", columnas);

        NegocioNacionalidad negocioNac = new NegocioNacionalidad();
        ArrayList<Nacionalidad> listaNacionalidades = negocioNac.obtenerTodasLasNacionalidades();
        request.setAttribute("listaNacionalidades", listaNacionalidades);
        
        NegocioProvincia negocioProv = new NegocioProvincia();
        ArrayList<Provincia> listaProvincias = negocioProv.obtenerTodasLasProvincias();
        request.setAttribute("listaProvincias", listaProvincias);
       
        
        String busqueda = request.getParameter("busqueda");
        String filtro = request.getParameter("filtro");

        ArrayList<Cliente> listaClientes;

        if (busqueda != null && filtro != null && !busqueda.isEmpty() && !filtro.isEmpty()) {
            listaClientes = negocio.obtenerClientesPorFiltro(filtro, busqueda);
        } else {
            listaClientes = negocio.ObtenerListadoClientes();
        }
        // Paginación
        int registrosPorPagina = 10;
        int paginaActual = 1;
        if (request.getParameter("pagina") != null) {
            try {
                paginaActual = Integer.parseInt(request.getParameter("pagina"));
            } catch (NumberFormatException e) {
                paginaActual = 1;
            }
        }
        int desde = (paginaActual - 1) * registrosPorPagina;
        int hasta = Math.min(desde + registrosPorPagina, listaClientes.size());
        ArrayList<Cliente> pagina = new ArrayList<>(listaClientes.subList(desde, hasta));

        int totalPaginas = (int) Math.ceil((double) listaClientes.size() / registrosPorPagina);

        request.setAttribute("ListaCli", pagina);
        request.setAttribute("paginaActual", paginaActual);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("busqueda", busqueda);
        request.setAttribute("filtroSeleccionado", filtro);

        RequestDispatcher rd = request.getRequestDispatcher("Formularios/ModoBanco/ABMLClientes/ListadoClientes.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
