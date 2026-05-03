// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package utils;

import java.util.List;

/**
 * Interfaz genérica que define las operaciones CRUD básicas.
 *
 * MPO — Ampliación de Programación:
 * El uso de interfaces obliga a que todos los servicios implementen
 * los mismos métodos, garantizando coherencia y mantenibilidad.
 *
 * @param <T>  El tipo de entidad (Socio, Espacio, Reserva...)
 * @param <ID> El tipo del identificador (normalmente Integer)
 */
public interface CrudService<T, ID> {

    /**
     * Guarda una nueva entidad en la base de datos.
     * @param entidad objeto a guardar
     * @return true si se guardó correctamente
     */
    boolean guardar(T entidad);

    /**
     * Devuelve todas las entidades de la tabla.
     * @return lista con todos los registros
     */
    List<T> listarTodos();

    /**
     * Busca una entidad por su identificador.
     * @param id identificador único
     * @return la entidad encontrada, o null si no existe
     */
    T buscarPorId(ID id);

    /**
     * Elimina una entidad por su identificador.
     * @param id identificador único
     * @return true si se eliminó correctamente
     */
    boolean eliminar(ID id);
}
