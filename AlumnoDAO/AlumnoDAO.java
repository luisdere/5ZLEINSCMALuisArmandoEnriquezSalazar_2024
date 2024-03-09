import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlumnoDAO {

    // Otros métodos CRUD...

    // Método para actualizar un alumno en la base de datos
    public void actualizarAlumno(Alumno alumno) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionDB.obtenerConexion(); // Suponiendo que tienes una clase para gestionar la conexión a la base de datos
            String query = "UPDATE alumnos SET nombre=?, edad=?, curso=? WHERE id=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, alumno.getNombre());
            stmt.setInt(2, alumno.getEdad());
            stmt.setString(3, alumno.getCurso());
            stmt.setInt(4, alumno.getId());
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Alumno actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el alumno.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión y el statement
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
