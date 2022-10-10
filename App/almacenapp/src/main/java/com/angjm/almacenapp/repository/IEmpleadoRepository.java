package com.angjm.almacenapp.repository;

import com.angjm.almacenapp.model.dto.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, String>  {

    Empleado findByNombreAndApellido(String nombre,String apellido);

}
