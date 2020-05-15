package com.primefaces.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jean
 */
public class Conexion {

    public Conexion() {
    }

    public Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            //nombre base de datos, nombre usuario, contraseña
            Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/futbol", "postgres", "111ZZZ...");
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error en la conexion", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void close(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexion", ex);
            ex.printStackTrace(System.out);
        }
    }

    public void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexion", ex);
            ex.printStackTrace(System.out);
        }
    }

    public void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexion", ex);
            ex.printStackTrace(System.out);
        }
    }

}
