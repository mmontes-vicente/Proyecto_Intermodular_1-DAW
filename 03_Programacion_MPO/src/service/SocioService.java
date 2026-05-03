// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package service;

import dao.SocioDAO;
import model.Socio;
import utils.Validador;

import java.util.List;

/**
 * Servicio de socios.
 * Contiene la logica de negocio. El acceso a base de datos
 * lo delega en SocioDAO.
 */
public class SocioService {

    private final SocioDAO dao = new SocioDAO();

    // --- ALTA ---

    public boolean altaSocio(Socio socio) {
        if (!Validador.esTextoValido(socio.getNombre(),    "nombre"))    return false;
        if (!Validador.esTextoValido(socio.getApellidos(), "apellidos")) return false;
        if (!Validador.esEmailValido(socio.getEmail()))                  return false;
        if (!Validador.esMembresiaValida(socio.getTipoMembresia()))      return false;

        boolean ok = dao.insertar(socio);
        if (ok) System.out.println("Socio dado de alta: " + socio.getNombre());
        return ok;
    }

    // --- LISTADOS ---

    public List<Socio> listarSocios() {
        return dao.findAll();
    }

    public Socio buscarPorId(int id) {
        if (!Validador.esIdValido(id)) return null;
        Socio s = dao.findById(id);
        if (s == null) System.out.println("No se encontro ningun socio con ID: " + id);
        return s;
    }

    public List<Socio> buscarPorMembresia(String tipo) {
        if (!Validador.esMembresiaValida(tipo)) return List.of();
        return dao.findByMembresia(tipo);
    }

    // --- ACTUALIZACION ---

    public boolean actualizarMembresia(int id, String nuevaMembresia) {
        if (!Validador.esIdValido(id))                    return false;
        if (!Validador.esMembresiaValida(nuevaMembresia)) return false;

        boolean ok = dao.actualizarMembresia(id, nuevaMembresia);
        if (ok) System.out.println("Membresia actualizada.");
        else    System.out.println("No se encontro el socio con ID: " + id);
        return ok;
    }

    // --- BAJA ---

    public boolean bajaSocio(int id) {
        if (!Validador.esIdValido(id)) return false;

        boolean ok = dao.eliminar(id);
        if (ok) System.out.println("Socio eliminado.");
        else    System.out.println("No se encontro el socio con ID: " + id);
        return ok;
    }

    // --- ESTADISTICAS ---

    public void mostrarEstadisticas() {
        dao.contarPorMembresia();
    }
}
