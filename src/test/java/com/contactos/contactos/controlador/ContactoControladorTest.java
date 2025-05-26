package com.contactos.contactos.controlador;

import com.contactos.contactos.entidad.Contacto;
import com.contactos.contactos.servicio.ContactoServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactoControladorTest {

    @InjectMocks
    private ContactoControlador controlador;

    @Mock
    private ContactoServicio servicio;

    @Test
    void testTodosLosContactos() {
        Contacto c1 = new Contacto();
        Contacto c2 = new Contacto();
        List<Contacto> contactos = Arrays.asList(c1, c2);
        when(servicio.todosContactos()).thenReturn(contactos);

        List<Contacto> resultado = controlador.todosLosContactos();

        assertEquals(2, resultado.size());
        verify(servicio, times(1)).todosContactos();
    }

    @Test
    void testObtenerPorId_Encontrado() {
        Long id = 1L;
        Contacto contacto = new Contacto();
        when(servicio.buscarId(id)).thenReturn(Optional.of(contacto));

        ResponseEntity<Contacto> respuesta = controlador.obtenerPorId(id);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(contacto, respuesta.getBody());
        verify(servicio).buscarId(id);
    }

    @Test
    void testObtenerPorId_NoEncontrado() {
        Long id = 1L;
        when(servicio.buscarId(id)).thenReturn(Optional.empty());

        ResponseEntity<Contacto> respuesta = controlador.obtenerPorId(id);

        assertEquals(404, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
    }

    @Test
    void testObtenerPornombre() {
        String nombre = "Carlos";
        Contacto contacto = new Contacto();
        when(servicio.buscarPorNombre(nombre)).thenReturn(List.of(contacto));

        List<Contacto> resultado = controlador.obtenerPornombre(nombre);

        assertEquals(1, resultado.size());
        verify(servicio).buscarPorNombre(nombre);
    }

    @Test
    void testCrearContacto() {
        Contacto nuevo = new Contacto();
        when(servicio.guardarContacto(nuevo)).thenReturn(nuevo);

        ResponseEntity<Contacto> respuesta = controlador.crearContacto(nuevo);

        assertEquals(201, respuesta.getStatusCodeValue());
        assertEquals(nuevo, respuesta.getBody());
        verify(servicio).guardarContacto(nuevo);
    }

    @Test
    void testEditarContacto_Existe() {
        Long id = 1L;
        Contacto actualizado = new Contacto();
        actualizado.setId(id);
        when(servicio.buscarId(id)).thenReturn(Optional.of(new Contacto()));
        when(servicio.guardarContacto(actualizado)).thenReturn(actualizado);

        ResponseEntity<Contacto> respuesta = controlador.editarContacto(id, actualizado);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(actualizado, respuesta.getBody());
        verify(servicio).guardarContacto(actualizado);
    }

    @Test
    void testEditarContacto_NoExiste() {
        Long id = 1L;
        Contacto actualizado = new Contacto();
        when(servicio.buscarId(id)).thenReturn(Optional.empty());

        ResponseEntity<Contacto> respuesta = controlador.editarContacto(id, actualizado);

        assertEquals(404, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
    }

    @Test
    void testEliminarContacto_Existe() {
        Long id = 1L;
        when(servicio.buscarId(id)).thenReturn(Optional.of(new Contacto()));

        ResponseEntity<Void> respuesta = controlador.eliminarContacto(id);

        assertEquals(204, respuesta.getStatusCodeValue());
        verify(servicio).eliminarContacto(id);
    }

    @Test
    void testEliminarContacto_NoExiste() {
        Long id = 1L;
        when(servicio.buscarId(id)).thenReturn(Optional.empty());

        ResponseEntity<Void> respuesta = controlador.eliminarContacto(id);

        assertEquals(404, respuesta.getStatusCodeValue());
        verify(servicio, never()).eliminarContacto(id);
    }
}
