package com.controller.bean;

import com.primefaces.dao.ContactoEmergenciaDAO;
import com.primefaces.dao.JugadorDAO;
import com.primefaces.dao.LesionJugadorDAO;
import com.primefaces.dto.ContactoEmergencia;
import com.primefaces.dto.Jugador;
import com.primefaces.dto.LesionJugador;
import com.primefaces.dto.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Jean Cortes
 */
@ManagedBean
@ViewScoped
public class ListaBeanLesionJugador {

    private List<LesionJugador> LesionJugadores;
    private LesionJugadorDAO lesionJugadorDAO;

    public List<LesionJugador> getLesionJugadores() {
        return LesionJugadores;
    }

    public void setLesionJugadores(List<LesionJugador> LesionJugadores) {
        this.LesionJugadores = LesionJugadores;
    }

   
    public ListaBeanLesionJugador() {
        lesionJugadorDAO = new LesionJugadorDAO();
    }

    @PostConstruct
    public void init() {
        LesionJugadores = cargarLesionesJugadores();
    }

    private List<LesionJugador> cargarLesionesJugadores() {
        LesionJugadorDAO lesionJugadorDAO = new LesionJugadorDAO();
        return lesionJugadorDAO.obtenerTodos();
    }

    public String salir() {
        return "index";
    }
}
