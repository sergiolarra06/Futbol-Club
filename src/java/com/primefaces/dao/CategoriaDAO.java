package com.primefaces.dao;

import com.primefaces.dto.Categoria;
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
public class CategoriaDAO implements IOperaciones<Categoria, Long> {

    private static final String SQL_SELECT_INNER_JOIN = "";

    private static final String SQL_SELECT = "SELECT id, nombre\n"
            + "	FROM public.categoria";

    private static final String SQL_SELECT_BY_ID = "SELECT  id, nombre\n"
            + "	FROM public.categoria WHERE id = ?";

    private static final String INSERT = "INSERT INTO public.categoria(id, nombre)\n"
            + "	VALUES (?, ?)";

    private static final String UPDATE = "";

    private static final String DELETE = "";

    @Override
    public int insertar(Categoria categoria) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, categoria.getId());
                stmt.setString(2, categoria.getNombre());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, "Error al insertar estatura", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public int modificar(Categoria categoria) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(UPDATE);

                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, "Error al insertar contacto de emergencia", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public int eliminar(Categoria estatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria obtener(Long id) {
        List<Categoria> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getLong(1));
                    categoria.setNombre(rs.getString(2));
                    dato.add(categoria);

                    return categoria;

                }
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public List<Categoria> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion
        List<Categoria> categorias = new ArrayList<>();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {//Iteramos cada uno de los elementos que tengamos en el rs
                    //Recuperamos los campos de nuestra entidad
                    Long id = rs.getLong("id");
                    String nombre = rs.getString("nombre");
                    //Creamos obj de la entidad correspondiente
                    Categoria categoria = new Categoria(id, nombre);
                    //agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    categorias.add(categoria);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return categorias;

    }

}
