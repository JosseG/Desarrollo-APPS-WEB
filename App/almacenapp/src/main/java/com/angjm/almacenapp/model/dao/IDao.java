package com.angjm.almacenapp.model.dao;

import java.util.List;

public interface IDao<T> {

    void insertar(T objeto);
    void actualizar(T objeto);
    void eliminar(Object o);
    List<T> obtenerTodo();
    T obtenerPorId(Object o);

}
