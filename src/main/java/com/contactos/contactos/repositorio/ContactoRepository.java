package com.contactos.contactos.repositorio;

import com.contactos.contactos.entidad.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
