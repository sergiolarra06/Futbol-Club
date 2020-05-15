package com.primefaces.dao;

import com.controller.bean.ListaBeanPersonas;
import com.primefaces.dto.ContactoEmergencia;
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
public class ContactoEmergenciaDAO implements IOperaciones<ContactoEmergencia, Long> {

    private static final String SQL_SELECT_INNER_JOIN = "SELECT persona.id, persona.nombre, persona.apellido,\n"
            + "	   contacto_emergencia.telefono\n"
            + "FROM persona INNER JOIN contacto_emergencia ON persona.id = contacto_emergencia.id";

    private static final String SQL_SELECT = "SELECT id, telefono\n"
            + "	FROM public.contacto_emergencia";

    private static final String SQL_SELECT_BY_ID = "SELECT  id, telefono\n"
            + "	FROM public.contacto_emergencia WHERE id = ?";

    private static final String INSERT = "INSERT INTO public.contacto_emergencia(\n"
            + "	id, telefono)\n"
            + "	VALUES (?, ?)";

    private static final String UPDATE = "";

    private static final String DELETE = "";

    @Override
    public int insertar(ContactoEmergencia contactoEmergencia) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, contactoEmergencia.getId());
                stmt.setString(2, contactoEmergencia.getTelefono());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ContactoEmergenciaDAO.class.getName()).log(Level.SEVERE, "Error al insertar contacto de emergencia", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public void modificar(ContactoEmergencia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(ContactoEmergencia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContactoEmergencia obtener(Long id) {
        List<ContactoEmergencia> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {

                    ContactoEmergencia contactoEmergencia = new ContactoEmergencia();
                    contactoEmergencia.setId(resultSet.getInt(1));
                    contactoEmergencia.setNombre(resultSet.getString(2));

                    dato.add(contactoEmergencia);

                    return contactoEmergencia;

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
    public List<ContactoEmergencia> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion
        List<ContactoEmergencia> contactoEmergencias = new ArrayList<>();

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
                    String telefono = rs.getString("telefono");
                    //Creamos obj de la entidad correspondiente 
                    ContactoEmergencia contactoEmergencia = new ContactoEmergencia(id, nombre, apellido, telefono);
                    //agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    contactoEmergencias.add(contactoEmergencia);

                }

            } catch (SQLException ex) {
                Logger.getLogger(ContactoEmergenciaDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return contactoEmergencias;

    }

}
