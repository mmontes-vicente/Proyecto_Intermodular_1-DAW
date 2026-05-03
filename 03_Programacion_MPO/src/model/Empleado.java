// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package model;

import java.time.LocalDate;

/**
 * Representa a un empleado del coworking.
 *
 * MPO — Herencia:
 * Extiende Persona igual que Socio. Así demostramos que la herencia
 * tiene sentido: Socio y Empleado COMPARTEN atributos de Persona,
 * pero cada uno tiene sus propios datos adicionales.
 *
 * Relación con Persona: Empleado ES-UNA Persona (herencia "es-un")
 */
public class Empleado extends Persona {

    private String    cargo;
    private double    salario;
    private LocalDate fechaContratacion;

    // ---- Constructores ----

    public Empleado() {
        super();
    }

    /** Constructor completo — usado al leer de la BD */
    public Empleado(int id, String nombre, String apellidos, String email,
                    String cargo, double salario, LocalDate fechaContratacion) {
        super(id, nombre, apellidos, email);
        this.cargo              = cargo;
        this.salario            = salario;
        this.fechaContratacion  = fechaContratacion;
    }

    /** Constructor sin ID — usado al crear un empleado nuevo */
    public Empleado(String nombre, String apellidos, String email,
                    String cargo, double salario, LocalDate fechaContratacion) {
        super(0, nombre, apellidos, email);
        this.cargo              = cargo;
        this.salario            = salario;
        this.fechaContratacion  = fechaContratacion;
    }

    // ---- Getters y Setters ----

    public int       getIdEmpleado()                              { return id; }
    public String    getCargo()                                   { return cargo; }
    public void      setCargo(String cargo)                       { this.cargo = cargo; }
    public double    getSalario()                                 { return salario; }
    public void      setSalario(double salario)                   { this.salario = salario; }
    public LocalDate getFechaContratacion()                       { return fechaContratacion; }
    public void      setFechaContratacion(LocalDate fecha)        { this.fechaContratacion = fecha; }

    /** Implementación obligatoria del método abstracto de Persona */
    @Override
    public String getRol() {
        return "EMPLEADO";
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-15s | %-20s | %-20s | %.2f€ |",
                id, nombre, apellidos, cargo, salario);
    }
}
