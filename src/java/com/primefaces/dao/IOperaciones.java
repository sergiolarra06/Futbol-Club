package com.primefaces.dao;

import java.util.List;

/**
 *
 * @author jean.cortes
 */
public interface IOperaciones<T, K> {//Tipo de T=dato,K=identificador 

    int insertar(T obj);

    void modificar(T obj);

    void eliminar(T obj);

    List<T> obtenerTodos();

    T obtener(K id);

}
