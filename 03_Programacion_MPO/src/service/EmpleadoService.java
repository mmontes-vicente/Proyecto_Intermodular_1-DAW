// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package service;

import dao.EmpleadoDAO;
import model.Empleado;
import utils.CrudService;
import utils.Validador;

import java.util.List;

/**
 * Servicio de empleados — modulo extra del MPO.
 * Implementa CrudService y delega el acceso a datos en EmpleadoDAO.
 */
public class EmpleadoService implements CrudService<Empleado, Integer> {

    private final EmpleadoDAO dao = new EmpleadoDAO();

    @Override
    public boolean guardar(Empleado e) {
        if (!Validador.esTextoValido(e.getNombre(),    "nombre"))    return false;
        if (!Validador.esTextoValido(e.getApellidos(), "apellidos")) return false;
        if (!Validador.esTextoValido(e.getCargo(),     "cargo"))     return false;
        if (!Validador.esPrecioValido(e.getSalario()))               return false;

        boolean ok = dao.insertar(e);
        if (ok) System.out.println("Empleado registrado: " + e.getNombreCompleto());
        return ok;
    }

    @Override
    public List<Empleado> listarTodos() {
        return dao.findAll();
    }

    @Override
    public Empleado buscarPorId(Integer id) {
        if (!Validador.esIdValido(id)) return null;
        Empleado emp = dao.findById(id);
        if (emp == null) System.out.println("No existe ningun empleado con ID: " + id);
        return emp;
    }

    @Override
    public boolean eliminar(Integer id) {
        if (!Validador.esIdValido(id)) return false;
        boolean ok = dao.eliminar(id);
        if (ok) System.out.println("Empleado eliminado.");
        else    System.out.println("No existe ningun empleado con ID: " + id);
        return ok;
    }
}
