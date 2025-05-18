package com.contactos.contactos.controlador;

import com.contactos.contactos.entidad.Contacto;
import com.contactos.contactos.repositorio.ContactoRepository;
import com.contactos.contactos.servicio.ContactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Contactos")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactoControlador {
    @Autowired
    private ContactoServicio servicio;
    @Autowired
    private ContactoRepository repository;


    @GetMapping
    public List<Contacto> todosLosContactos(){
        return servicio.todosContactos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacto> obtenerPorId(@PathVariable Long id) {
        Optional<Contacto> contacto = servicio.buscarId(id);
        return contacto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Contacto> obtenerPornombre(@RequestParam("nombre") String nombre) {
        return servicio.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Contacto> crearContacto(@RequestBody Contacto contacto) {
        Contacto nuevoContacto = servicio.guardarContacto(contacto);
        return new ResponseEntity<>(nuevoContacto, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Contacto> editarContacto(@PathVariable Long id, @RequestBody Contacto contactoActualizado) {
        Optional<Contacto> contactoExistente = servicio.buscarId(id);
        if (contactoExistente.isPresent()) {
            contactoActualizado.setId(id); // Asegurar que el ID sea el correcto
            Contacto contactoGuardado = servicio.guardarContacto(contactoActualizado);
            return new ResponseEntity<>(contactoGuardado, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Long id) {
        if (servicio.buscarId(id).isPresent()) {
            servicio.eliminarContacto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
