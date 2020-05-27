/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.dao;

import com.primefaces.dto.Gol;
import com.primefaces.dto.Jugador;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergio
 */
public class GolDAO implements IOperaciones<Gol, Long> {
    private static final String INSERT = "INSERT INTO public.gol(\n"
            + "	jugador_id, fecha, gol)\n"
            + "	VALUES (?, ?, ?)";

    private static final String SELECT = "SELECT jugador_id, fecha, gol\n"
            + "	FROM public.gol";

    private static final String SELECT_BY_ID = "SELECT jugador_id, fecha, gol\n"
            + "	FROM public.gol WHERE jugador_id = ?";

    private static final String SELECT_BY_GOL = "SELECT jugador_id, fecha, gol\n"
            + "	FROM public.gol WHERE gol= ?";

    private static final String UPDATE = "UPDATE public.gol\n"
            + "	SET jugador_id=?, fecha=?, gol=?\n"
            + "	WHERE fecha=?";
    
    private static final String DELETE = "DELETE FROM public.gol\n"
            + "	WHERE fecha=?";

    @Override
    public int insertar(Gol gol) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, gol.getJugador().getId());
                stmt.setDate(2, (java.sql.Date) gol.getFecha());
                stmt.setInt(3, gol.getGol());
                
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al insertar gol", ex);
            } finally {

            }
        }
        return 0;
    }
    @Override
    public List<Gol> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion
        List<Gol> goles = new ArrayList<>();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SELECT);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {//Iteramos cada uno de los elementos que tengamos en el rs
                    //Recuperamos los campos de nuestra entidad
                    JugadorDAO jugadorDAO = new JugadorDAO();
                    Jugador jugador = jugadorDAO.obtener(Long.parseLong(rs.getString("jugador_id")));
                    Date fecha = rs.getDate("fecha");
                    int gol = rs.getInt("gol");
                    //Creamos obj de la entidad correspondiente 
                    Gol gol1 = new Gol(jugador, fecha, gol);
                    //agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    goles.add(gol1);

                }

            } catch (SQLException ex) {
                Logger.getLogger(GolDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return goles;
    }

    @Override
    public Gol obtener(Long id) {
        List<Gol> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    Gol gol = new Gol();
                    gol.setJugador((Jugador) rs.getObject(1));
                    gol.setFecha(rs.getDate(2));
                    gol.setGol(rs.getInt(3));

                    dato.add(gol);

                    return gol;

                }
            } catch (SQLException ex) {
                Logger.getLogger(GolDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public int modificar(Gol gol) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(UPDATE);
                stmt.setLong(1, gol.getJugador().getId());
                stmt.setDate(2, gol.getFecha());
                stmt.setInt(3, gol.getGol());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(GolDAO.class.getName()).log(Level.SEVERE, "Error al modificar gol", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public int eliminar(Gol gol) {
        Connection conn = new Conexion().getConnection();
        int filas = 0;
        if(conn != null){
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(DELETE);
                stmt.setLong(1, gol.getJugador().getId());
                return filas = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(GolDAO.class.getName()).log(Level.SEVERE, "Error al eliminar gol", ex);
            } finally {

            }
        }
        return filas;
    }

   

}

