/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.dao;

import com.primefaces.dto.Gol;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.PaseGol;
import com.primefaces.dto.Peso;
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
public class PesoDAO implements IOperaciones<Peso, Long> {

    private static final String INSERT = "INSERT INTO public.peso(\n"
            + "	jugador_id, fecha, peso)\n"
            + "	VALUES (?, ?, ?)";

    private static final String SELECT = "SELECT jugador_id, fecha, peso\n"
            + "	FROM public.peso";

    private static final String SELECT_BY_ID = "SELECT jugador_id, fecha, peso\n"
            + "	FROM public.peso WHERE jugador_id = ?";

    private static final String UPDATE = "UPDATE public.peso\n"
            + "	SET jugador_id=?, fecha=?, peso=?\n"
            + "	WHERE fecha=?";

    private static final String DELETE = "DELETE FROM public.peso\n"
            + "	WHERE jugador_id=?";

    @Override
    public int insertar(Peso peso) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, peso.getJugador().getId());
                stmt.setDate(2, (java.sql.Date) peso.getFecha());
                stmt.setFloat(3, peso.getPeso());
                
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al insertar Peso", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public List<Peso> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion
        List<Peso> pesos = new ArrayList<>();

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
                    int peso = rs.getInt("peso");
                    //Creamos obj de la entidad correspondiente 
                    Peso peso1 = new Peso(jugador, fecha, peso);
                    //agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    pesos.add(peso1);

                }

            } catch (SQLException ex) {
                Logger.getLogger(PesoDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return pesos;
    }

    @Override
    public Peso obtener(Long id) {
        List<Peso> dato = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    Peso peso = new Peso();
                    peso.setJugador((Jugador) rs.getObject(1));
                    peso.setFecha(rs.getDate(2));
                    peso.setPeso(rs.getInt(3));

                    dato.add(peso);

                    return peso;

                }
            } catch (SQLException ex) {
                Logger.getLogger(PesoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public int modificar(Peso peso) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(UPDATE);
                stmt.setLong(1, peso.getJugador().getId());
                stmt.setDate(2, peso.getFecha());
                stmt.setFloat(3, peso.getPeso());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PesoDAO.class.getName()).log(Level.SEVERE, "Error al modificar peso", ex);
            } finally {

            }
        }
        return 0;
    }

    @Override
    public int eliminar(Peso peso) {
        Connection conn = new Conexion().getConnection();
        int filas = 0;
        if(conn != null){
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(DELETE);
                stmt.setLong(1, peso.getJugador().getId());
                return filas = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PesoDAO.class.getName()).log(Level.SEVERE, "Error al eliminar gol", ex);
            } finally {

            }
        }
        return filas;
    }

}
