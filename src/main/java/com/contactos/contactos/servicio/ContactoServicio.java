package com.contactos.contactos.servicio;

import com.contactos.contactos.entidad.Contacto;
import com.contactos.contactos.repositorio.ContactoRepository;
import com.contactos.contactos.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Contacto> contactos = repository.findAll();
        List<Contacto> result = new ArrayList<>();

        for (Contacto fly : contactos) {
           if (fly.getPersona().getNombre().equals(nombre)){
               result.add(fly);
           }
        }
        return result;
    }

    public Contacto guardarContacto(Contacto contacto) {
        personaRepository.save(contacto.getPersona());
        return repository.save(contacto);
    }

    public void eliminarContacto(Long id) {
        repository.deleteById(id);
    }

}
