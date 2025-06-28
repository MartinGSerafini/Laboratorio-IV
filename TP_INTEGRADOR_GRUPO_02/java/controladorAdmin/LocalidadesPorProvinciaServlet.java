package controladorAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Localidad;
import negocio.NegocioLocalidad;

@WebServlet("/LocalidadesPorProvinciaServlet")
public class LocalidadesPorProvinciaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProvinciaStr = request.getParameter("idProvincia");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            int idProvincia = Integer.parseInt(idProvinciaStr);

            NegocioLocalidad negocioLocalidad = new NegocioLocalidad();
            List<Localidad> localidades = negocioLocalidad.obtenerLocalidadesPorProvincia(idProvincia);
            out.print("[");
            for (int i = 0; i < localidades.size(); i++) {
                Localidad loc = localidades.get(i);
                out.print("{");
                out.print("\"id\":" + loc.getId() + ",");
                out.print("\"nombre\":\"" + loc.getLocalidad().replace("\"", "\\\"") + "\"");
                out.print("}");
                if (i < localidades.size() - 1) out.print(",");
            }
            out.print("]");

        } catch (NumberFormatException e) {
            out.print("[]");
        }
        out.close();
    }
}
