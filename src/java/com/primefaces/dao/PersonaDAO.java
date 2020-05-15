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

    private static final String UPDATE = "UPDATE public.persona\n"
            + "	SET  nombre=?, apellido=?, fecha_nacimiento=?, correo=?\n"
            + "	WHERE id = ?";

    private static final String DELETE = "DELETE FROM public.persona\n"
            + "	WHERE id = ?";

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
    public void modificar(Persona o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Persona o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

                    dato.add(persona);

                    return persona;

                }
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return personas;

    }

    public List<Persona> test() {
        Conexion conexion = new Conexion();// Connection conn = Conexion.getConnection();
        Connection conn = conexion.getConnection();
        List<Persona> personas = new ArrayList<>();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {

                    Long id = resultSet.getLong("id");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                    String correo = resultSet.getString("correo");

                    Persona persona = new Persona(id, nombre, apellido, (java.sql.Date) fechaNacimiento, correo);
                    personas.add(persona);

                }
            } catch (SQLException ex) {
                Logger.getLogger(ListaBeanPersonas.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return personas;
    }

}
