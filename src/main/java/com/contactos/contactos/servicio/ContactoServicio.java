package com.contactos.contactos.servicio;

import com.contactos.contactos.entidad.Contacto;
import com.contactos.contactos.entidad.Persona;
import com.contactos.contactos.repositorio.ContactoRepository;
import com.contactos.contactos.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactoServicio {

    @Autowired
    private ContactoRepository repository;
    @Autowired
    private PersonaRepository personaRepository;

    public List<Contacto> todosContactos() {
        return repository.findAll();
    }

    public Optional<Contacto> buscarId(long id) {
        return repository.findById(id);
    }

    public List<Contacto> buscarPorNombre(String nombre) {
        System.out.println("Nombre recibido: " + nombre);

        List<Contacto> contactos = repository.findAll();
        System.out.println("Total contactos encontrados: " + contactos.size());

        List<Contacto> result = new ArrayList<>();

        for (Contacto fly : contactos) {
            if (fly.getPersona() != null && fly.getPersona().getNombre() != null) {
                System.out.println("Comparando con: " + fly.getPersona().getNombre());
                if (fly.getPersona().getNombre().trim().equalsIgnoreCase(nombre.trim()))
                {
                    result.add(fly);
                }
            }
        }

        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron contactos con el nombre: " + nombre);
        }

        return result;
    }



    public Persona buscarPorId(Long id) {
        Persona result = null;
        List<Persona> todasLasPersonas = personaRepository.findAll();

        for (Persona persona : todasLasPersonas) {
            if (persona.getId().equals(id)) {
                return result = persona;
            }
        }
        return result;

    }

    public Contacto guardarContacto(Contacto contacto) {
        if (contacto == null || contacto.getPersona() == null) {
            throw new IllegalArgumentException("El contacto o su persona es nula");
        }

        Persona personaGuardada = personaRepository.save(contacto.getPersona());
        contacto.setPersona(personaGuardada);
        return repository.save(contacto);
    }




    public void eliminarContacto(Long id) {
        repository.deleteById(id);
    }

}
