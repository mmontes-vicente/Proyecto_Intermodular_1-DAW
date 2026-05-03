// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package model;

/**
 * Clase modelo que representa un Espacio del coworking.
 * Módulo: Programación (0485) — POO
 */
public class Espacio {

    private int     idEspacio;
    private String  nombreEspacio;
    private String  tipo;            // escritorio, sala_reunion, sala_evento, cabina
    private int     capacidad;
    private double  precioHora;
    private boolean disponible;

    // ---- Constructores ----

    public Espacio() {}

    public Espacio(int idEspacio, String nombreEspacio, String tipo,
                   int capacidad, double precioHora, boolean disponible) {
        this.idEspacio    = idEspacio;
        this.nombreEspacio = nombreEspacio;
        this.tipo         = tipo;
        this.capacidad    = capacidad;
        this.precioHora   = precioHora;
        this.disponible   = disponible;
    }

    // ---- Getters y Setters ----

    public int getIdEspacio()                     { return idEspacio; }
    public void setIdEspacio(int idEspacio)       { this.idEspacio = idEspacio; }

    public String getNombreEspacio()                      { return nombreEspacio; }
    public void setNombreEspacio(String nombreEspacio)    { this.nombreEspacio = nombreEspacio; }

    public String getTipo()               { return tipo; }
    public void setTipo(String tipo)      { this.tipo = tipo; }

    public int getCapacidad()                 { return capacidad; }
    public void setCapacidad(int capacidad)   { this.capacidad = capacidad; }

    public double getPrecioHora()                 { return precioHora; }
    public void setPrecioHora(double precioHora)  { this.precioHora = precioHora; }

    public boolean isDisponible()                 { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-22s | %-14s | Cap: %-2d | %.2f€/h | %s |",
                idEspacio, nombreEspacio, tipo, capacidad, precioHora,
                disponible ? " Disponible" : " Ocupado");
    }
}
