// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package dao;

import db.Conexion;
import model.Empleado;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla empleado.
 */
public class EmpleadoDAO {

    // --- CREATE ---

    public boolean insertar(Empleado e) {
        String sql = "INSERT INTO empleado (nombre, apellidos, cargo, email, salario, fecha_contratacion) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNombre().trim());
            ps.setString(2, e.getApellidos().trim());
            ps.setString(3, e.getCargo().trim());
            ps.setString(4, e.getEmail());
            ps.setDouble(5, e.getSalario());
            ps.setDate(6, Date.valueOf(
                e.getFechaContratacion() != null ? e.getFechaContratacion() : LocalDate.now()
            ));
            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Ya existe un empleado con ese email.");
        } catch (SQLException ex) {
            System.err.println("Error al insertar empleado: " + ex.getMessage());
        }
        return false;
    }

    // --- READ ---

    public List<Empleado> findAll() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado ORDER BY apellidos, nombre";

        try (Connection con = Conexion.getConexion();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
        }
        return lista;
    }

    public Empleado findById(int id) {
        String sql = "SELECT * FROM empleado WHERE id_empleado = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            System.err.println("Error al buscar empleado: " + e.getMessage());
        }
        return null;
    }

    // --- DELETE ---

    public boolean eliminar(int id) {
        String sql = "DELETE FROM empleado WHERE id_empleado = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        }
        return false;
    }

    // --- MAPEO privado: ResultSet -> Empleado ---

    private Empleado mapear(ResultSet rs) throws SQLException {
        return new Empleado(
            rs.getInt("id_empleado"),
            rs.getString("nombre"),
            rs.getString("apellidos"),
            rs.getString("email"),
            rs.getString("cargo"),
            rs.getDouble("salario"),
            rs.getDate("fecha_contratacion") != null
                ? rs.getDate("fecha_contratacion").toLocalDate() : null
        );
    }
}
