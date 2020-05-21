package com.primefaces.dao;

import com.controller.bean.ListaBeanPersonas;
import com.controller.bean.BeanPosicion;
import com.primefaces.dto.ContactoEmergencia;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.Pago;
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
public class PagoDAO implements IOperaciones<Pago, Long> {

    private static final String SQL_SELECT_INNER_JOIN = "SELECT jugador.id,\n"
            + "persona.nombre,\n"
            + "pago.id, pago.fecha, pago.periodo_pago, pago.valor\n"
            + "FROM persona INNER JOIN jugador ON persona.id = jugador.id\n"
            + "INNER JOIN pago ON persona.id = pago.jugador_id";
    //n"
    //,  jugador.contacto_emergencia_id

    private static final String SQL_SELECT = "SELECT id, jugador_id, fecha, periodo_pago, valor\n"
            + "	FROM public.pago";

    private static final String SQL_SELECT_BY_ID = "SELECT id, contacto_emergencia_id, posicion\n"
            + "	FROM public.jugador WHERE id = ?";

    private static final String INSERT = "INSERT INTO public.pago(\n"
            + "	id, jugador_id, fecha, periodo_pago, valor)\n"
            + "	VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE public.jugador\n"
            + "	SET  nombre=?, apellido=?, fecha_nacimiento=?, correo=?\n"
            + "	WHERE id = ?";

    private static final String DELETE = "DELETE FROM public.pago\n"
            + "	WHERE id = ?";

    @Override
    public int insertar(Pago pago) {
        Connection conn = new Conexion().getConnection();
        if (conn != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(INSERT);
                stmt.setLong(1, pago.getId());
                stmt.setLong(2, pago.getJugador().getId());
                stmt.setDate(3, (java.sql.Date) pago.getFecha());
                stmt.setString(4, pago.getPeriodoPago());
                stmt.setFloat(5, pago.getValor());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, "Error al insertar pago", ex);
            } finally {
            }
        }
        return 0;

    }

    @Override
    public int modificar(Pago pago) {

        Connection conn = new Conexion().getConnection();
        int filas = 0;

        if (conn != null) {
            PreparedStatement stmt;

            try {
                stmt = conn.prepareStatement(UPDATE);

                stmt.setLong(1, pago.getJugador().getId());
                stmt.setDate(2, (java.sql.Date) pago.getFecha());
                stmt.setString(3, pago.getPeriodoPago());
                stmt.setFloat(4, pago.getValor());

                stmt.setLong(5, pago.getId());

                return filas = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, "Error al insertar pago", ex);
            } finally {

            }
        }
        return filas;
    }

    @Override
    public int eliminar(Pago jugador) {

        return 0;
    }

    @Override
    public Pago obtener(Long id) {
        List<Pago> pagos = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                stmt.setLong(1, id);
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {

                    Pago pago = new Pago();
                    pago.setId(resultSet.getLong(1));
                    pago.setJugador((Jugador) resultSet.getObject(2));
                    pago.setFecha(resultSet.getDate(3));
                    pago.setPeriodoPago(resultSet.getString(4));
                    pago.setValor(resultSet.getFloat(5));

                    pagos.add(pago);

                    return pago;

                }
            } catch (SQLException ex) {
                Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return null;
    }

    @Override
    public List<Pago> obtenerTodos() {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();//Realiza la conexion

        List<Pago> pagos = new ArrayList<>();

        if (conexion != null) {
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {//Iteramos cada uno de los elementos que tengamos en el rs
                    //Recuperamos los campos de nuestra entidad
                    Long id = rs.getLong("id");
                    JugadorDAO jugadorDAO = new JugadorDAO();
                    Jugador jugador = jugadorDAO.obtener(Long.parseLong(rs.getString("jugador_id")));
                    Date fecha = rs.getDate("fecha");
                    String periodoPago = rs.getString("periodo_pago");
                    Float valor = rs.getFloat("valor");
                    //Creamos obj de la entidad correspondiente 
                    Pago pago = new Pago(id, jugador, fecha, periodoPago, valor);
                    //Agregamos nuestro objeto de tipo contacto de emergencia a la lista de contacto de emergencias
                    pagos.add(pago);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PagoDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                conexion.close(conn);
            }
        }
        return pagos;
    }

}
