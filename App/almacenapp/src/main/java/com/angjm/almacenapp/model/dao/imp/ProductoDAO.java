package com.angjm.almacenapp.model.dao.imp;

import com.angjm.almacenapp.model.dao.IDao;
import com.angjm.almacenapp.model.dto.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ProductoDAO implements IDao<Producto> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void insertar(Producto objeto) {
        entityManager.persist(objeto);
    }

    @Override
    public void actualizar(Producto objeto) {
        entityManager.merge(objeto);
    }

    @Override
    public void eliminar(Object o) {
        entityManager.remove((Producto)entityManager.find(Producto.class,o));
    }

    @Override
    public List<Producto> obtenerTodo() {
        String consulta = "Select p FROM Producto p";

        return (List<Producto>)entityManager.createQuery(consulta).getResultList();
    }

    @Override
    public Producto obtenerPorId(Object o) {
        return (Producto)entityManager.find(Producto.class,o);
    }
}
