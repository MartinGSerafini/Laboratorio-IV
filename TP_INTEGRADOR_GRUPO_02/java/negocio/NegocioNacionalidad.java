package negocio;

import java.util.ArrayList;
import dao.NacionalidadDao;
import entidades.Nacionalidad;

public class NegocioNacionalidad {
    private NacionalidadDao dao = new NacionalidadDao();

    public ArrayList<Nacionalidad> obtenerTodasLasNacionalidades() {
        return dao.obtenerNacionalidades();
    }
}