package com.contactos.contactos.repositorio;

import com.contactos.contactos.entidad.Contacto;
import com.contactos.contactos.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByNombreContainingIgnoreCase(String nombre);
}
