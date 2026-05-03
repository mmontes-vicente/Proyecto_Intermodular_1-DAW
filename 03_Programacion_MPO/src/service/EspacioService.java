// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package service;

import dao.EspacioDAO;
import model.Espacio;
import utils.Validador;

import java.util.List;

/**
 * Servicio de espacios.
 * Delega el acceso a datos en EspacioDAO.
 */
public class EspacioService {

    private final EspacioDAO dao = new EspacioDAO();

    public List<Espacio> listarEspacios() {
        return dao.findAll();
    }

    public List<Espacio> listarDisponibles() {
        return dao.findDisponibles();
    }

    public boolean cambiarDisponibilidad(int id, boolean disponible) {
        if (!Validador.esIdValido(id)) return false;

        boolean ok = dao.actualizarDisponibilidad(id, disponible);
        if (ok) System.out.println("Disponibilidad actualizada.");
        else    System.out.println("No se encontro el espacio con ID: " + id);
        return ok;
    }
}
