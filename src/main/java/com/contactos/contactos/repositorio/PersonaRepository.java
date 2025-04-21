package com.contactos.contactos.repositorio;

import com.contactos.contactos.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
