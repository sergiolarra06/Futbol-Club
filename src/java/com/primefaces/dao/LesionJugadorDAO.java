package com.primefaces.dao;

import com.controller.bean.ListaBeanPersonas;
import com.controller.bean.BeanPosicion;
import com.primefaces.dto.ContactoEmergencia;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.Lesion;
import com.primefaces.dto.LesionJugador;

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
public class LesionJugadorDAO implements IOperaciones<LesionJugador, Long> {

    private static final String SQL_SELECT_INNER_JOIN = "SELECT persona.nombre,\n"
            + "	 fecha, jugador_id, lesion_id\n"
            + "	   FROM persona INNER JOIN lesion_jugador ON persona.id = lesion_jugador.jugador_id";

    private static final String SQL_SELECT = "SELECT fecha, jugador_id, lesion_id\n"
            + "	FROM public.lesion_jugador";

    private static final String SQL_SELECT_BY_ID = "SELECT fecha, jugador_id, lesion_id\n"
            + "	FROM public.lesion_jugador WHERE lesion_id=?";

    private static final String INSERT = "INSERT INTO public.lesion_jugador(\n"
            + "	fecha, jugador_id, lesion_id)\n"
            + "	VALUES (?, ?, ?)";

    private static final String UPDATE = "UPDATE public.lesion_jugador\n"
            + "	SET fecha=?, jugador_id=?, lesion_id=?\n"
            + "	WHERE lesion_id=?";

    private static final String DELETE = "DELETE FROM public.lesion_jugador\n"
            + "	WHERE lesion_id=?";

    @Override
    public int insertar(LesionJugador lesionJugador) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setDate(1, (java.sql.Date) lesionJugador.getFecha());
                stmt.setLong(2, lesionJugador.getJugador().getId());
                stmt.setLong(3, lesionJugador.getLesion().getId());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(LesionJugadorDAO.class.getName()).log(Level.SEVERE, "Error al insertar lesionJugador", ex);
            } finally {
            }
        }
        return 0;

    }

    @Override
    public int modificar(LesionJugador lesionJugador) {
        Connection conn = new Conexion().getConnection();
        int filas = 0;
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(UPDATE);
                stmt.setLong(1, lesionJugador.getLesion().getId());
                stmt.setDate(2, lesionJugador.getFecha());
                stmt.setLong(3, lesionJugador.getJugador().getId());
                stmt.setLong(4, lesionJugador.getLesion().getId());
                return filas = stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al insertar lesionJugador", ex);
            } finally {

            }
        }
        return filas;
    }

    @Override
    public int eliminar(LesionJugador lesionJugador) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(DELETE);
//                stmt.setDate(1, (java.sql.Date) lesionJugador.getFecha());
//                stmt.setLong(2, lesionJugador.getJugador().getId());
                stmt.setLong(1, lesionJugador.getLesion().getId());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(LesionJugadorDAO.class.getName()).log(Level.SEVERE, "Error al eliminar lesionJugador", ex);
            } finally {
            }
        }
        return 0;
    }

    @Override
    public LesionJugador obtener(Long id) {
        List<LesionJugador> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {

                    LesionJugador lesionJugador = new LesionJugador();
                    lesionJugador.setFecha(resultSet.getDate(1));
                    lesionJugador.setJugador((Jugador) resultSet.getObject(2));
                    lesionJugador.setLesion((Lesion) resultSet.getObject(3));

                    dato.add(lesionJugador);

                    return lesionJugador;

                }
            } catch (SQLException ex) {
                Logger.getLogger(ContactoEmergenciaDAO.class.getName()).log(Level.SEVERE, "Error al obtener por id", ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public List<LesionJugador> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion

        List<LesionJugador> lesionJugadores = new ArrayList<>();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {//Iteramos cada uno de los elementos que tengamos en el rs
                    //Recuperamos los campos de nuestra entidad
                    Date fecha = rs.getDate("fecha");
                    JugadorDAO jugadorDAO = new JugadorDAO();
                    Jugador jugador = jugadorDAO.obtener(Long.parseLong(rs.getString("jugador_id")));
                    LesionDAO lesionDAO = new LesionDAO();
                    Lesion lesion = lesionDAO.obtener(Long.parseLong(rs.getString("lesion_id")));
                    //Creamos obj de la entidad correspondiente 
                    LesionJugador lesionJugador = new LesionJugador(fecha, jugador, lesion);
                    //Agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    lesionJugadores.add(lesionJugador);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LesionJugadorDAO.class.getName()).log(Level.SEVERE, "Error al obtener todos", ex);
            } finally {
                conexion.close(conn);
            }
        }
        return lesionJugadores;
    }

}
