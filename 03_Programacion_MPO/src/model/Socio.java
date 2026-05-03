// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package model;

import java.time.LocalDate;

/**
 * Representa a un socio del coworking.
 *
 * MPO — Herencia:
 * Extiende Persona igual que Empleado. id, nombre, apellidos y email
 * se heredan de Persona. Socio añade solo lo que le es propio:
 * telefono, fechaAlta y tipoMembresia.
 */
public class Socio extends Persona {

    private String    telefono;
    private LocalDate fechaAlta;
    private String    tipoMembresia;

    // ---- Constructores ----

    public Socio() {
        super();
    }

    /** Constructor completo — usado al leer de la BD */
    public Socio(int idSocio, String nombre, String apellidos,
                 String email, String telefono,
                 LocalDate fechaAlta, String tipoMembresia) {
        super(idSocio, nombre, apellidos, email);
        this.telefono      = telefono;
        this.fechaAlta     = fechaAlta;
        this.tipoMembresia = tipoMembresia;
    }

    /** Constructor sin ID — usado al dar de alta un socio nuevo */
    public Socio(String nombre, String apellidos, String email,
                 String telefono, LocalDate fechaAlta, String tipoMembresia) {
        super(0, nombre, apellidos, email);
        this.telefono      = telefono;
        this.fechaAlta     = fechaAlta;
        this.tipoMembresia = tipoMembresia;
    }

    // ---- Getters y Setters propios ----

    public int       getIdSocio()                              { return id; }
    public String    getTelefono()                             { return telefono; }
    public void      setTelefono(String telefono)              { this.telefono = telefono; }
    public LocalDate getFechaAlta()                            { return fechaAlta; }
    public void      setFechaAlta(LocalDate fechaAlta)         { this.fechaAlta = fechaAlta; }
    public String    getTipoMembresia()                        { return tipoMembresia; }
    public void      setTipoMembresia(String tipoMembresia)    { this.tipoMembresia = tipoMembresia; }

    /** Implementación obligatoria del método abstracto de Persona */
    @Override
    public String getRol() {
        return "SOCIO";
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-15s | %-20s | %-30s | %-10s |",
                id, nombre, apellidos, email, tipoMembresia);
    }
}
