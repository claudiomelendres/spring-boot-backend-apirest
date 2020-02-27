package com.maveric.springboot.backend.apirest.models.dao;

import com.maveric.springboot.backend.apirest.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;  // esto era antes de hacer la paginacion

public interface IClienteDao extends JpaRepository<Cliente, Long> {
}
