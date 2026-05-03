// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package model;

import java.time.LocalDateTime;

/**
 * Representa una reserva de un espacio por un socio.
 *
 * MPO — Composición:
 * Una Reserva TIENE-UN Socio y TIENE-UN Espacio (composición/asociación).
 * No heredamos, porque una Reserva no ES un Socio ni un Espacio.
 * La relación correcta aquí es "tiene-un", no "es-un".
 *
 * También contiene lógica de negocio básica:
 * calcularPrecio() y esValida() con sus reglas de negocio.
 */
public class Reserva {

    // Estados posibles de una reserva (constantes — buena práctica)
    public static final String ESTADO_PENDIENTE  = "pendiente";
    public static final String ESTADO_CONFIRMADA = "confirmada";
    public static final String ESTADO_COMPLETADA = "completada";
    public static final String ESTADO_CANCELADA  = "cancelada";

    private int            idReserva;
    private int            idSocio;
    private int            idEspacio;
    private LocalDateTime  fechaInicio;
    private LocalDateTime  fechaFin;
    private String         estado;
    private double         precioTotal;

    // Objetos completos (para mostrar info sin hacer más consultas SQL)
    private Socio   socio;
    private Espacio espacio;

    // ---- Constructores ----

    public Reserva() {}

    /** Constructor con IDs — usado para crear una reserva nueva */
    public Reserva(int idSocio, int idEspacio,
                   LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.idSocio    = idSocio;
        this.idEspacio  = idEspacio;
        this.fechaInicio = fechaInicio;
        this.fechaFin   = fechaFin;
        this.estado     = ESTADO_PENDIENTE;
    }

    /** Constructor completo — usado al leer de la BD */
    public Reserva(int idReserva, int idSocio, int idEspacio,
                   LocalDateTime fechaInicio, LocalDateTime fechaFin,
                   String estado, double precioTotal) {
        this.idReserva  = idReserva;
        this.idSocio    = idSocio;
        this.idEspacio  = idEspacio;
        this.fechaInicio = fechaInicio;
        this.fechaFin   = fechaFin;
        this.estado     = estado;
        this.precioTotal = precioTotal;
    }

    // ---- Lógica de negocio ----

    /**
     * Calcula el precio total basándose en el precio/hora del espacio
     * y la duración de la reserva.
     * MPO — método con responsabilidad clara y única.
     */
    public double calcularPrecio(double precioHoraEspacio) {
        if (fechaInicio == null || fechaFin == null) return 0;
        long minutos = java.time.Duration.between(fechaInicio, fechaFin).toMinutes();
        double horas = minutos / 60.0;
        return Math.round(horas * precioHoraEspacio * 100.0) / 100.0;
    }

    /**
     * Valida que la reserva tiene sentido:
     * - La fecha de fin debe ser posterior a la de inicio
     * - La duración mínima es 30 minutos
     * MPO — reglas de negocio bien definidas en el modelo.
     */
    public boolean esValida() {
        if (fechaInicio == null || fechaFin == null) return false;
        long minutos = java.time.Duration.between(fechaInicio, fechaFin).toMinutes();
        return minutos >= 30;
    }

    /**
     * Comprueba si la reserva está activa (pendiente o confirmada).
     */
    public boolean estaActiva() {
        return ESTADO_PENDIENTE.equals(estado) || ESTADO_CONFIRMADA.equals(estado);
    }

    // ---- Getters y Setters ----

    public int           getIdReserva()                           { return idReserva; }
    public void          setIdReserva(int idReserva)              { this.idReserva = idReserva; }
    public int           getIdSocio()                             { return idSocio; }
    public void          setIdSocio(int idSocio)                  { this.idSocio = idSocio; }
    public int           getIdEspacio()                           { return idEspacio; }
    public void          setIdEspacio(int idEspacio)              { this.idEspacio = idEspacio; }
    public LocalDateTime getFechaInicio()                         { return fechaInicio; }
    public void          setFechaInicio(LocalDateTime f)          { this.fechaInicio = f; }
    public LocalDateTime getFechaFin()                            { return fechaFin; }
    public void          setFechaFin(LocalDateTime f)             { this.fechaFin = f; }
    public String        getEstado()                              { return estado; }
    public void          setEstado(String estado)                 { this.estado = estado; }
    public double        getPrecioTotal()                         { return precioTotal; }
    public void          setPrecioTotal(double precioTotal)       { this.precioTotal = precioTotal; }
    public Socio         getSocio()                               { return socio; }
    public void          setSocio(Socio socio)                    { this.socio = socio; }
    public Espacio       getEspacio()                             { return espacio; }
    public void          setEspacio(Espacio espacio)              { this.espacio = espacio; }

    @Override
    public String toString() {
        String nombreSocio   = (socio   != null) ? socio.getNombreCompleto()   : "ID:" + idSocio;
        String nombreEspacio = (espacio != null) ? espacio.getNombreEspacio()  : "ID:" + idEspacio;
        return String.format("| %-4d | %-20s | %-18s | %-20s | %-12s | %.2f€ |",
                idReserva, nombreSocio, nombreEspacio,
                fechaInicio != null ? fechaInicio.toString() : "-",
                estado, precioTotal);
    }
}
