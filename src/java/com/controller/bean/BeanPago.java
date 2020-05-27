package com.controller.bean;

import com.primefaces.dao.PagoDAO;
import com.primefaces.dto.ContactoEmergencia;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.Pago;
import com.primefaces.dto.Pago;
import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
//import org.primefaces.event.RowEditEvent;
//import javax.validation.constraints.Email;

/**
 *
 * @author Jean Cortes
 */
//@Named(value = "beanv")
//@Dependent
@ManagedBean
@ViewScoped
public class BeanPago implements Serializable {

    /**
     * Creates a new instance of BeanVehiculo
     */
    private long id;
    private Jugador jugador;
    private Date fecha;
    private String periodoPago;
    private Map<String, String> periodoPagos = new HashMap<String, String>();
    private float valor;

    public BeanPago() {
        jugador = new Jugador();

    }

    @PostConstruct
    public void init() {

        //posiciones
        periodoPagos = new HashMap<String, String>();
        periodoPagos.put("Enero", "Enero");
        periodoPagos.put("Febrero", "Febrero");
        periodoPagos.put("Marzo", "Marzo");
        periodoPagos.put("Abril", "Abril");
        periodoPagos.put("Mayo", "Mayo");
        periodoPagos.put("Junio", "Junio");
        periodoPagos.put("Julio", "Julio");
        periodoPagos.put("Agosto", "Agosto");
        periodoPagos.put("Septiembre", "Septiembre");
        periodoPagos.put("Octubre", "Octubre");
        periodoPagos.put("Noviembre", "Noviembre");
        periodoPagos.put("Diciembre", "Diciembre");

    }

    public void insertar() {
        if (jugador == null || fecha == null || periodoPago == null || periodoPago.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Datos Nulos Revise"));
            return;
        }
        Pago pago = new Pago();
        pago.setId(id);
        pago.setJugador(jugador);
        pago.setFecha(new java.sql.Date(fecha.getTime()));
        pago.setPeriodoPago(periodoPago);
        pago.setValor(valor);

        PagoDAO pagoDAO = new PagoDAO();
        int rta = pagoDAO.insertar(pago);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se registro la persona"));

        System.out.println("rta " + rta);
    }

    public void modificar() {

        Pago pago = new Pago();
        pago.setId(id);
        pago.setJugador(jugador);
        pago.setFecha(new java.sql.Date(fecha.getTime()));
        pago.setPeriodoPago(periodoPago);
        pago.setValor(valor);

        PagoDAO pagoDAO = new PagoDAO();
        int rta = pagoDAO.modificar(pago);

        if (rta > 0) {
            //this.mensaje = "actualizo "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Actualizo " + rta + " Pago"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NO, Actualizo"));

        }
        System.out.println("rta " + rta);

    }

    public void eliminar() {

        Pago pago = new Pago();
        pago.setId(id);
//        persona.setNombre(nombre);
//        persona.setApellido(apellido);
//        persona.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
//        persona.setCorreo(correo);

        PagoDAO pagoDAO = new PagoDAO();
        int rta = pagoDAO.modificar(pago);
        if (rta > 0) {
            //this.mensaje = "eliminar "+r;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Eliminado " + rta + " Pago"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No, Elimino"));

        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(String periodoPago) {
        this.periodoPago = periodoPago;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Map<String, String> getPeriodoPagos() {
        return periodoPagos;
    }

    public void setPeriodoPagos(Map<String, String> periodoPagos) {
        this.periodoPagos = periodoPagos;
    }

    public String regresar() {
        return "index";
    }

}
