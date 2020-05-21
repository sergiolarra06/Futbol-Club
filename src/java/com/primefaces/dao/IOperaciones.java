package com.primefaces.dao;

import java.util.List;

/**
 *
 * @author jean.cortes
 */
public interface IOperaciones<T, K> {//Tipo de T=dato,K=identificador 

    int insertar(T obj);
     

    int modificar(T obj);

    int eliminar(T obj);

    List<T> obtenerTodos();

    T obtener(K id);

}
