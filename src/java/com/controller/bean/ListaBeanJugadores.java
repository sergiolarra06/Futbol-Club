package com.controller.bean;

import com.primefaces.dao.ContactoEmergenciaDAO;
import com.primefaces.dao.JugadorDAO;
import com.primefaces.dto.ContactoEmergencia;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author OscarEsteban
 */
@ManagedBean
@ViewScoped
public class ListaBeanJugadores extends Persona {

    private List<Jugador> Jugadores;
    private JugadorDAO jugadorDAO;

    public List<Jugador> getJugadores() {
        return Jugadores;
    }

    public void setJugadores(List<Jugador> Jugadores) {
        this.Jugadores = Jugadores;
    }

    public ListaBeanJugadores() {
        jugadorDAO = new JugadorDAO();
    }

    @PostConstruct
    public void init() {
        Jugadores = cargarJugadores();
    }

    private List<Jugador> cargarJugadores() {
        JugadorDAO jugadorDAO = new JugadorDAO();
        return jugadorDAO.obtenerTodos();
    }

    public String salir() {
        return "index";
    }

}
