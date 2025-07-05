package controladorAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import negocio.NegocioCuenta;

@WebServlet("/ListadoCuentasServlet")
public class ListadoCuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NegocioCuenta negocio = new NegocioCuenta();

        ArrayList<String> columnas = negocio.obtenerColumnasCuentas();
        request.setAttribute("columnas", columnas);

        String busqueda = request.getParameter("busqueda");
        String filtro = request.getParameter("filtro");

        ArrayList<Cuenta> listaCuentas;

        if (busqueda != null && filtro != null && !busqueda.isEmpty() && !filtro.isEmpty()) {
            listaCuentas = negocio.obtenerCuentasPorFiltro(filtro, busqueda);
        } else {
            listaCuentas = negocio.ObtenerListadoCuentas();
        }

        // Paginaci�n
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
        int hasta = Math.min(desde + registrosPorPagina, listaCuentas.size());
        ArrayList<Cuenta> pagina = new ArrayList<>(listaCuentas.subList(desde, hasta));

        int totalPaginas = (int) Math.ceil((double) listaCuentas.size() / registrosPorPagina);

        request.setAttribute("ListaCue", pagina);
        request.setAttribute("paginaActual", paginaActual);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("busqueda", busqueda);
        request.setAttribute("filtroSeleccionado", filtro);

        RequestDispatcher rd = request.getRequestDispatcher("Formularios/ModoBanco/ABMLCuentas/ListarCuentas.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
