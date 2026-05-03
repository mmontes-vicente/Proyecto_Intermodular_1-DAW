// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package service;

import dao.ReservaDAO;
import utils.Validador;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/**
 * Servicio de reservas.
 * La logica de calculo de horas y precio vive aqui.
 * El acceso a base de datos lo delega en ReservaDAO.
 */
public class ReservaService {

    private final ReservaDAO dao = new ReservaDAO();
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // --- CREAR RESERVA ---

    public boolean crearReserva(int idSocio, int idEspacio,
                                String fechaInicio, String fechaFin) {
        if (!Validador.esIdValido(idSocio))   return false;
        if (!Validador.esIdValido(idEspacio)) return false;

        double precioHora  = dao.getPrecioHoraEspacio(idEspacio);
        double horas       = calcularHoras(fechaInicio, fechaFin);
        double precioTotal = Math.round(precioHora * horas * 100.0) / 100.0;

        boolean ok = dao.insertar(idSocio, idEspacio, fechaInicio, fechaFin, precioTotal);
        if (ok) System.out.printf("Reserva creada. Precio total: %.2f%n", precioTotal);
        return ok;
    }

    // --- LISTAR ---

    public void listarReservas() {
        dao.listarConJoin();
    }

    // --- CAMBIAR ESTADO ---

    public boolean cambiarEstado(int idReserva, String nuevoEstado) {
        if (!Validador.esIdValido(idReserva))      return false;
        if (!Validador.esEstadoValido(nuevoEstado)) return false;

        boolean ok = dao.actualizarEstado(idReserva, nuevoEstado);
        if (ok) System.out.println("Estado actualizado a: " + nuevoEstado);
        else    System.out.println("No se encontro la reserva con ID: " + idReserva);
        return ok;
    }

    public boolean cancelarReserva(int idReserva) {
        return cambiarEstado(idReserva, "cancelada");
    }

    // --- ESTADISTICAS ---

    public void mostrarIngresos() {
        dao.mostrarIngresos();
    }

    // --- Calculo de horas (logica de negocio, no va en el DAO) ---

    private double calcularHoras(String inicio, String fin) {
        try {
            LocalDateTime dtInicio = LocalDateTime.parse(inicio, FMT);
            LocalDateTime dtFin    = LocalDateTime.parse(fin, FMT);
            return Duration.between(dtInicio, dtFin).toMinutes() / 60.0;
        } catch (Exception e) {
            return 1.0;
        }
    }
}
