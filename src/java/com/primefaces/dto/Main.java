/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.dto;

import com.primefaces.dao.Conexion;
import com.primefaces.dao.PersonaDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jean.cortes
 */
public class Main {

    public static void main(String[] args) {

//        ContactoEmergencia e = new ContactoEmergencia(1, "Paola", "Ortiz", "3102037299");
//
//        Posicion delantero = Posicion.DELANTERO;    // Instancia de un enum de la clase Demarcación
//        Posicion defensa = Posicion.DEFENSA;
//        System.out.println("" + delantero.name());   // Devuelve un String con el nombre de la constante (DELANTERO)
//        System.out.println("" + delantero.toString());    // Devuelve un String con el nombre de la constante (DELANTERO)
//        System.out.println("" + delantero.ordinal());    // Devuelve un entero con la posición del enum según está declarada (3).
//        System.out.println("" + delantero.compareTo(defensa));    // Compara el enum con el parámetro según el orden en el que están declarados lo enum
//        System.out.println("" + Posicion.values().length);// Devuelve un array que contiene todos los enum
//
//        for (Posicion p : Posicion.values()) {
//            System.out.println(p.toString());
//        }
//
//        Jugador j = new Jugador(1, "jean", " cortés", new Date(12 / 12 / 12), " j@.com", Posicion.PORTERO, e);
//        System.out.println(j);
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/futbol", "postgres", "111ZZZ...");
//            Persona persona = new PersonaDAO(conn);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Conexion conexion = new Conexion();
    }

}
