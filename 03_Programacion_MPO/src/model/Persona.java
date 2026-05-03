// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package model;

/**
 * Clase abstracta que representa a cualquier persona del sistema.
 *
 * MPO — Herencia:
 * Tanto Socio como Empleado son personas con nombre, apellidos y email.
 * Extraemos esos atributos comunes aquí para evitar duplicidad de código
 * (principio DRY: Don't Repeat Yourself).
 *
 * Al ser abstracta, no se puede instanciar directamente:
 * no tiene sentido crear una "Persona" genérica, solo Socios o Empleados.
 */
public abstract class Persona {

    // Atributos comunes a cualquier persona del sistema
    protected int    id;
    protected String nombre;
    protected String apellidos;
    protected String email;

    // ---- Constructor vacío ----
    public Persona() {}

    // ---- Constructor con datos básicos ----
    public Persona(int id, String nombre, String apellidos, String email) {
        this.id        = id;
        this.nombre    = nombre;
        this.apellidos = apellidos;
        this.email     = email;
    }

    // ---- Getters y Setters ----

    public int getId()              { return id; }
    public void setId(int id)       { this.id = id; }

    public String getNombre()               { return nombre; }
    public void setNombre(String nombre)    { this.nombre = nombre; }

    public String getApellidos()                    { return apellidos; }
    public void setApellidos(String apellidos)      { this.apellidos = apellidos; }

    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }

    /**
     * Método abstracto: cada subclase debe devolver
     * una descripción de su rol en el sistema.
     */
    public abstract String getRol();

    /**
     * Devuelve el nombre completo (nombre + apellidos).
     * Disponible para todas las subclases sin repetir código.
     */
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", getRol(), getNombreCompleto(), email);
    }
}
