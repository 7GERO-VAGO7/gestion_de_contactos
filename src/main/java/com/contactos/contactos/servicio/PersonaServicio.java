package com.contactos.contactos.servicio;

import com.contactos.contactos.entidad.Contacto;
import com.contactos.contactos.entidad.Persona;
import com.contactos.contactos.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServicio {
    @Autowired
    public PersonaRepository person;

    public Persona AgregarPersona(Persona persona) {
        return person.save(persona);
    }


}
