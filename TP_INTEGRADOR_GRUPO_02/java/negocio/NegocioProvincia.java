package negocio;

import java.util.List;
import dao.ProvinciaDao;
import entidades.Provincia;

public class NegocioProvincia {
    private ProvinciaDao dao = new ProvinciaDao();

    public List<Provincia> obtenerTodasLasProvincias() {
        return dao.obtenerProvincias();
    }
}
