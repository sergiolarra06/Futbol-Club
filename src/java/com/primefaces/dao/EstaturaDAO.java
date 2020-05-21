package com.primefaces.dao;

import com.controller.bean.ListaBeanEstatura;
import com.primefaces.dto.Estatura;
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
public class EstaturaDAO implements IOperaciones<Estatura, Long> {

    private static final String SQL_SELECT_INNER_JOIN = "SELECT persona.id, persona.nombre, persona.apellido,\n"
            + "	   contacto_emergencia.telefono\n"
            + "FROM persona INNER JOIN contacto_emergencia ON persona.id = contacto_emergencia.id";

    private static final String SQL_SELECT = "SELECT jugador_id, fecha, estatura\n"
            + "	FROM public.estatura";

    private static final String SQL_SELECT_BY_ID = "SELECT  id, telefono\n"
            + "	FROM public.contacto_emergencia WHERE id = ?";

    private static final String INSERT = "INSERT INTO public.estatura(\n" +
"	jugador_id, fecha, estatura)\n" +
"	VALUES (?, ?, ?)";

    private static final String UPDATE = "UPDATE public.contacto_emergencia\n"
            + "	SET id=?, telefono=?\n"
            + "	WHERE id = ?";

    private static final String DELETE = "";

    @Override
    public int insertar(Estatura estatura) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, estatura.getJugador().getId());
                stmt.setDate(2, (java.sql.Date) estatura.getFecha());
                stmt.setDouble(3, estatura.getEstatura());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EstaturaDAO.class.getName()).log(Level.SEVERE, "Error al insertar estatura", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public int modificar(Estatura estatura) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(UPDATE);
                stmt.setLong(1, estatura.getJugador().getId());
                stmt.setDate(2, estatura.getFecha());
                stmt.setDouble(3, estatura.getEstatura());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EstaturaDAO.class.getName()).log(Level.SEVERE, "Error al insertar contacto de emergencia", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public int eliminar(Estatura estatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estatura obtener(Long id) {
        List<Estatura> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    Estatura estatura = new Estatura();
                    estatura.setJugador((Jugador) rs.getObject(1));
                    estatura.setFecha(rs.getDate(2));
                    estatura.setEstatura(rs.getDouble(3));

                    dato.add(estatura);

                    return estatura;

                }
            } catch (SQLException ex) {
                Logger.getLogger(EstaturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public List<Estatura> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion
        List<Estatura> estaturas = new ArrayList<>();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {//Iteramos cada uno de los elementos que tengamos en el rs
                    //Recuperamos los campos de nuestra entidad
                    JugadorDAO jugadorDAO = new JugadorDAO();
                    Jugador jugador = jugadorDAO.obtener(Long.parseLong(rs.getString("jugador_id")));
                    Date fecha = rs.getDate("fecha");
                    Double estatura = rs.getDouble("estatura");
                    //Creamos obj de la entidad correspondiente 
                    Estatura estatura1 = new Estatura(jugador, fecha, estatura);
                    //agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    estaturas.add(estatura1);

                }

            } catch (SQLException ex) {
                Logger.getLogger(EstaturaDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return estaturas;

    }

}
