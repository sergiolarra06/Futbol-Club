package com.primefaces.dao;

import com.controller.bean.ListaBeanPersonas;
import com.controller.bean.BeanPosicion;
import com.primefaces.dto.ContactoEmergencia;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.Posicion;
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
public class JugadorDAO implements IOperaciones<Jugador, Long> {

    private static final String SQL_SELECT_INNER_JOIN = "SELECT persona.id, persona.nombre, persona.apellido, persona.fecha_nacimiento, persona.correo,\n"
            + "	 jugador.posicion, jugador.contacto_emergencia_id\n"
            + "	   FROM persona INNER JOIN jugador ON persona.id = jugador.id";
//n"
    //,  jugador.contacto_emergencia_id

    private static final String SQL_SELECT = "SELECT id, jugador_id, fecha, periodo_pago, valor\n"
            + "	FROM public.pago";

    private static final String SQL_SELECT_BY_ID = "SELECT id, contacto_emergencia_id, posicion\n"
            + "	FROM public.jugador WHERE id = ?";

    private static final String INSERT = "INSERT INTO public.jugador(id, contacto_emergencia_id,  posicion) \n"
            + "VALUES (?, ?, ?)";
    private static final String INSERTP = "INSERT INTO public.persona(\n"
            + "	id, nombre, apellido, fecha_nacimiento, correo)\n"
            + "	VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE public.jugador\n"
            + "	SET  posicion=?\n"
            + "	WHERE id = ?";

    private static final String DELETE = "DELETE FROM public.jugador\n"
            + "	WHERE id = ?";

    @Override
    public int insertar(Jugador jugador) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, jugador.getId());
                stmt.setLong(2, jugador.getContactoEmergencia().getId());
                stmt.setString(3, jugador.getPosicion().toString());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(JugadorDAO.class.getName()).log(Level.SEVERE, "Error al insertar jugador", ex);
            } finally {
            }
        }
        return 0;
    }

    @Override
    public int modificar(Jugador jugador) {
        Connection conn = new Conexion().getConnection();
        int filas = 0;

        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(UPDATE);
                stmt.setString(1, jugador.getPosicion());
                stmt.setLong(2, jugador.getId());
                return filas = stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al insertar persona", ex);
            } finally {

            }
        }
        return filas;

    }

    @Override
    public int eliminar(Jugador jugador) {
        Connection conn = new Conexion().getConnection();
        int filas = 0;
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(DELETE);
                stmt.setLong(1, jugador.getId());
                return filas = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al eliminar persona", ex);
            } finally {

            }
        }
        return filas;
    }

    @Override
    public Jugador obtener(Long id) {
        List<Jugador> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {

                    Jugador jugador = new Jugador();
                    jugador.setId(resultSet.getInt(1));
                    jugador.setNombre(resultSet.getString(2));

                    dato.add(jugador);

                    return jugador;

                }
            } catch (SQLException ex) {
                Logger.getLogger(ContactoEmergenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public List<Jugador> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion
        //Connection conn = null;
        //PreparedStatement stmt = null;
        List<Jugador> jugadores = new ArrayList<>();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT_INNER_JOIN);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {//Iteramos cada uno de los elementos que tengamos en el rs
                    //Recuperamos los campos de nuestra entidad
                    Long id = rs.getLong("id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                    String correo = rs.getString("correo");
//                    String posicion = rs.getString("posicion");

                    String posicion = rs.getString("posicion");
                    ContactoEmergenciaDAO contactoEmergenciaDAO = new ContactoEmergenciaDAO();
                    ContactoEmergencia contactoEmergencia = contactoEmergenciaDAO.obtener(Long.parseLong(rs.getString("contacto_emergencia_id")));

                    //Creamos obj de la entidad correspondiente 
                    Jugador jugador = new Jugador(id, nombre, apellido, fechaNacimiento, correo, posicion, contactoEmergencia);
                    //Agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    jugadores.add(jugador);
                }
            } catch (SQLException ex) {
                Logger.getLogger(JugadorDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return jugadores;
    }

}
