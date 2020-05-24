package com.primefaces.dao;

import com.controller.bean.ListaBeanPersonas;
import com.primefaces.dto.Jugador;
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
public class PersonaDAO implements IOperaciones<Persona, Long> {

    private static final String SQL_SELECT = "SELECT id, nombre, apellido, fecha_nacimiento, correo\n"
            + "	FROM public.persona";

    private static final String SQL_SELECT_BY_ID = "SELECT id, nombre, apellido, fecha_nacimiento, correo\n"
            + "	FROM public.persona WHERE id = ?";

    private static final String INSERT = "INSERT INTO public.persona(\n"
            + "	id, nombre, apellido, fecha_nacimiento, correo)\n"
            + "	VALUES (?, ?, ?, ?, ?)";

    private static final String INSERTJ = "INSERT INTO public.jugador(id, contacto_emergencia_id,  posicion) \n"
            + "VALUES (?, ?, ?)";

    private static final String UPDATE = "UPDATE public.persona\n"
            + "	SET nombre=?, apellido=?, fecha_nacimiento=?, correo=?\n"
            + "	WHERE id = ?";

    private static final String DELETE = "DELETE FROM persona\n"
            + "	WHERE id = ?";

    @Override
    public int eliminar(Persona persona) {
        Connection conn = new Conexion().getConnection();
        int filas = 0;
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(DELETE);
                stmt.setLong(1, persona.getId());
                return filas = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al eliminar persona", ex);
            } finally {

            }
        }
        return filas;
    }

    @Override
    public int modificar(Persona persona) {
        Connection conn = new Conexion().getConnection();
        int filas = 0;
        if (conn != null) {
            PreparedStatement stmt;

            try {
                stmt = conn.prepareStatement(UPDATE);

                stmt.setString(1, persona.getNombre());
                stmt.setString(2, persona.getApellido());
                stmt.setDate(3, (java.sql.Date) persona.getFechaNacimiento());
                stmt.setString(4, persona.getCorreo());
                stmt.setLong(5, persona.getId());

                return filas = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al modificar persona", ex);
            } finally {

            }
        }
        return filas;
    }

    @Override
    public int insertar(Persona persona) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, persona.getId());
                stmt.setString(2, persona.getNombre());
                stmt.setString(3, persona.getApellido());
                stmt.setDate(4, (java.sql.Date) persona.getFechaNacimiento());
                stmt.setString(5, persona.getCorreo());

                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al insertar persona", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public Persona obtener(Long id) {
        List<Persona> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {

                    Persona persona = new Persona();
                    persona.setId(resultSet.getInt(1));
                    persona.setNombre(resultSet.getString(2));
                    persona.setApellido(resultSet.getString(3));
                    persona.setFechaNacimiento(resultSet.getDate(4));
                    persona.setCorreo(resultSet.getString(5));

                    dato.add(persona);
                    return persona;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al obtener persona por id", ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public List<Persona> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion
        //Connection conn = null;
        //PreparedStatement stmt = null;
        List<Persona> personas = new ArrayList<>();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {//Iteramos cada uno de los elementos que tengamos en el rs
                    //Recuperamos los campos de nuestra entidad
                    Long id = rs.getLong("id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                    String correo = rs.getString("correo");
                    //Creamos obj de la entidad correspondiente 
                    Persona persona = new Persona(id, nombre, apellido, (java.sql.Date) fechaNacimiento, correo);
                    //agregamos nuestro objeto de tipo persona a la lista de personas
                    personas.add(persona);

                }

            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class
                        .getName()).log(Level.SEVERE, "Error al obtener lista de personas", ex);
            } finally {
                conexion.close(conn);
            }
        }
        return personas;

    }

}
