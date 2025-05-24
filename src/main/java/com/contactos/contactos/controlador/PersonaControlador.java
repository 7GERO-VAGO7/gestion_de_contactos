package com.contactos.contactos.controlador;

import com.contactos.contactos.entidad.Persona;
import com.contactos.contactos.servicio.PersonaServicio;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaControlador {
    @Autowired
    public PersonaServicio personservice;

    @PutMapping
    public String crearPerson(@RequestBody Persona per) {
        personservice.AgregarPersona(per);
        return "persona creada";
    }
}
