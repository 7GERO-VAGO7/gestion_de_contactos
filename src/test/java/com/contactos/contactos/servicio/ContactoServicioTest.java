package com.contactos.contactos.servicio;

import com.contactos.contactos.entidad.Contacto;
import com.contactos.contactos.entidad.Persona;
import com.contactos.contactos.repositorio.ContactoRepository;
import com.contactos.contactos.repositorio.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactoServicioTest {

    @InjectMocks
    private ContactoServicio servicio;

    @Mock
    private ContactoRepository contactoRepository;

    @Mock
    private PersonaRepository personaRepository;

    @Test
    void testTodosContactos() {
        List<Contacto> mockContactos = List.of(new Contacto(), new Contacto());
        when(contactoRepository.findAll()).thenReturn(mockContactos);

        List<Contacto> resultado = servicio.todosContactos();

        assertEquals(2, resultado.size());
        verify(contactoRepository).findAll();
    }

    @Test
    void testBuscarId_Encontrado() {
        Long id = 1L;
        Contacto contacto = new Contacto();
        when(contactoRepository.findById(id)).thenReturn(Optional.of(contacto));

        Optional<Contacto> resultado = servicio.buscarId(id);

        assertTrue(resultado.isPresent());
        assertEquals(contacto, resultado.get());
    }

    @Test
    void testBuscarId_NoEncontrado() {
        Long id = 1L;
        when(contactoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Contacto> resultado = servicio.buscarId(id);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testBuscarPorNombre() {
        Persona persona1 = new Persona(); persona1.setNombre("Juan");
        Persona persona2 = new Persona(); persona2.setNombre("Ana");
        Contacto c1 = new Contacto(); c1.setPersona(persona1);
        Contacto c2 = new Contacto(); c2.setPersona(persona2);
        Contacto c3 = new Contacto(); c3.setPersona(persona1);
        List<Contacto> mockContactos = List.of(c1, c2, c3);

        when(contactoRepository.findAll()).thenReturn(mockContactos);

        List<Contacto> resultado = servicio.buscarPorNombre("Juan");

        assertEquals(2, resultado.size());
    }

    @Test
    void testBuscarPorId_Encontrado() {
        Persona p1 = new Persona(); p1.setId(1L);
        List<Persona> personas = List.of(p1);

        when(personaRepository.findAll()).thenReturn(personas);

        Persona resultado = servicio.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testBuscarPorId_NoEncontrado() {
        when(personaRepository.findAll()).thenReturn(List.of());

        Persona resultado = servicio.buscarPorId(1L);

        assertNull(resultado);
    }

    @Test
    void testGuardarContacto_Exitoso() {
        Persona persona = new Persona();
        persona.setId(1L);

        Contacto contacto = new Contacto();
        contacto.setPersona(persona);

        when(contactoRepository.save(any())).thenReturn(contacto);

        Contacto resultado = servicio.guardarContacto(contacto);

        assertNotNull(resultado);
        verify(personaRepository).save(persona);
        verify(contactoRepository).save(contacto);
    }

    @Test
    void testEliminarContacto() {
        Long id = 1L;

        servicio.eliminarContacto(id);

        verify(contactoRepository).deleteById(id);
    }
}
