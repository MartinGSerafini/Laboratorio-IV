package controladorAdmin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.GeneroClientes;
import entidades.MovimientoReporte;
import entidades.ProvinciaCantidad;
import negocio.NegocioInformes;

@WebServlet("/InformesServlet")
public class InformesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NegocioInformes negocio = new NegocioInformes();

        // Informe 1
        ArrayList<ProvinciaCantidad> listaTopProvincias = negocio.top3provincias();
        request.setAttribute("topProvincias", listaTopProvincias);

        // Informe 2
        ArrayList<GeneroClientes> generos = negocio.obtenerCantidadGenero();
        request.setAttribute("generoClientes", generos);

        // Informe 3
        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaFinStr = request.getParameter("fechaFin");

        if (fechaInicioStr != null && fechaFinStr != null && !fechaInicioStr.isEmpty() && !fechaFinStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = sdf.parse(fechaInicioStr);
                Date fechaFin = sdf.parse(fechaFinStr);
                
      

                ArrayList<MovimientoReporte> listaMovimientos = negocio.obtenerReporteMovimientos(fechaInicio, fechaFin);
                request.setAttribute("listaMovimientos", listaMovimientos);
                request.setAttribute("mostrarModalInforme3", true);
                
               
                for (MovimientoReporte r : listaMovimientos) {
                    System.out.println(r.getDetalleMov() + " - " + r.getImporteMov());
                }

                
          
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensajeError", "Formato de fecha inválido");
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Formularios/ModoBanco/Informes/Informes.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
