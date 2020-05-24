package com.primefaces.dao;

import com.controller.bean.ListaBeanLesion;
import com.primefaces.dto.Lesion;
import com.primefaces.dto.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jean.cortes
 */
public class LesionDAO implements IOperaciones<Lesion, Long> {

    private static final String SQL_SELECT = "SELECT id, descripcion\n"
            + "	FROM public.lesion;";

    private static final String SQL_SELECT_BY_ID = "SELECT id, descripcion FROM public.lesion WHERE id = ?";

    private static final String INSERT = "INSERT INTO public.lesion(\n"
            + "	id, descripcion)\n"
            + "	VALUES (?, ?)";

    private static final String UPDATE = "UPDATE public.lesion\n"
            + "	SET descripcion=?\n"
            + "	WHERE id = ?";

    private static final String DELETE = "DELETE FROM public.lesion\n"
            + "	WHERE id = ?";

    @Override
    public int insertar(Lesion lesion) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, lesion.getId());
                stmt.setString(2, lesion.getDescripcion());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(LesionDAO.class.getName()).log(Level.SEVERE, "Error al insertar estatura", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public int modificar(Lesion lesion) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(UPDATE);
                stmt.setString(1, lesion.getDescripcion());
                stmt.setLong(2, lesion.getId());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(LesionDAO.class.getName()).log(Level.SEVERE, "Error al modificar lesión", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public int eliminar(Lesion lesion) {
        Connection conn = new Conexion().getConnection();
        int filas = 0;
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(DELETE);
                stmt.setLong(1, lesion.getId());
                return filas = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al eliminar lesion", ex);
            } finally {

            }
        }
        return filas;
    }

    @Override
    public Lesion obtener(Long id) {
        List<Lesion> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    Lesion lesion = new Lesion();
                    lesion.setId(rs.getLong(1));
                    lesion.setDescripcion(rs.getString(2));
                    dato.add(lesion);
                    return lesion;

                }
            } catch (SQLException ex) {
                Logger.getLogger(LesionDAO.class.getName()).log(Level.SEVERE, "Error al obtener por id", ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public List<Lesion> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion
        List<Lesion> lesiones = new ArrayList<>();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {//Iteramos cada uno de los elementos que tengamos en el rs
                    //Recuperamos los campos de nuestra entidad
                    long id = rs.getLong("id");
                    String descripcion = rs.getString("descripcion");
                    //Creamos obj de la entidad correspondiente 
                    Lesion lesion = new Lesion(id, descripcion);
                    //agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    lesiones.add(lesion);

                }

            } catch (SQLException ex) {
                Logger.getLogger(LesionDAO.class
                        .getName()).log(Level.SEVERE, "Error al listar todos las lesiones", ex);
            } finally {
                conexion.close(conn);
            }
        }
        return lesiones;

    }

}
