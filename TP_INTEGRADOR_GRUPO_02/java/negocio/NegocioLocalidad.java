package negocio;

import java.util.List;
import dao.LocalidadDao;
import entidades.Localidad;

public class NegocioLocalidad {
    private LocalidadDao dao = new LocalidadDao();

    public List<Localidad> obtenerLocalidadesPorProvincia(int idProvincia) {
        return dao.obtenerLocalidadesPorProvincia(idProvincia);
    }
}
