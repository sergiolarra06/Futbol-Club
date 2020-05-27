/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.dao;

import com.primefaces.dto.Gol;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.PaseGol;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class PaseGolDAO implements IOperaciones<PaseGol, Long> {

    private static final String INSERT = "INSERT INTO public.pase_gol(\n"
            + "	jugador_id, fecha, pase_gol)\n"
            + "	VALUES (?, ?, ?)";

    private static final String SELECT = "SELECT jugador_id, fecha, pase_gol\n"
            + "	FROM public.pase_gol";

    private static final String SELECT_BY_ID = "SELECT jugador_id, fecha, pase_gol\n"
            + "	FROM public.pase_gol WHERE jugador_id = ?";

    private static final String SELECT_BY_PASE_GOL = "SELECT jugador_id, fecha, pase_gol\n"
            + "	FROM public.pase_gol WHERE pase_gol= ?";

    private static final String UPDATE = "UPDATE public.pase_gol\n"
            + "	SET jugador_id=?, fecha=?, pase_gol=?\n"
            + "	WHERE fecha=?";

    private static final String DELETE = "DELETE FROM public.pase_gol\n"
            + "	WHERE fecha=?";

    @Override
    public int insertar(PaseGol paseGol) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, paseGol.getJugador().getId());
                stmt.setDate(2, (java.sql.Date) paseGol.getFecha());
                stmt.setInt(3, paseGol.getPase_gol());

                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al insertar Pase gol", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public List<PaseGol> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion
        List<PaseGol> pases = new ArrayList<>();

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
                    int pasegol = rs.getInt("pase_gol");
                    //Creamos obj de la entidad correspondiente 
                    PaseGol pasegol1 = new PaseGol(jugador, fecha, pasegol);
                    //agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    pases.add(pasegol1);

                }

            } catch (SQLException ex) {
                Logger.getLogger(PaseGolDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return pases;
    }

    @Override
    public PaseGol obtener(Long id) {
        List<PaseGol> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    PaseGol pasegol = new PaseGol();
                    pasegol.setJugador((Jugador) rs.getObject(1));
                    pasegol.setFecha(rs.getDate(2));
                    pasegol.setPase_gol(rs.getInt(3));

                    dato.add(pasegol);

                    return pasegol;

                }
            } catch (SQLException ex) {
                Logger.getLogger(PaseGolDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public int modificar(PaseGol pasegol) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(UPDATE);
                stmt.setLong(1, pasegol.getJugador().getId());
                stmt.setDate(2, pasegol.getFecha());
                stmt.setInt(3, pasegol.getPase_gol());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PaseGolDAO.class.getName()).log(Level.SEVERE, "Error al modificar pase gol", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public int eliminar(PaseGol pasegol) {
        Connection conn = new Conexion().getConnection();
        int filas = 0;
        if(conn != null){
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(DELETE);
                stmt.setLong(1, pasegol.getJugador().getId());
                return filas = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PaseGolDAO.class.getName()).log(Level.SEVERE, "Error al eliminar pase gol", ex);
            } finally {

            }
        }
        return filas;
    }

}
