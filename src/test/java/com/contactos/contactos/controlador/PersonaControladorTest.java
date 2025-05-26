package com.contactos.contactos.controlador;

import com.contactos.contactos.entidad.Persona;
import com.contactos.contactos.servicio.PersonaServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaControladorTest {

    @InjectMocks
    private PersonaControlador personaControlador;

    @Mock
    private PersonaServicio personaServicio;

    @Test
    void testCrearPerson() {
        Persona persona = new Persona();

        String resultado = personaControlador.crearPerson(persona);

        assertEquals("persona creada", resultado);
        verify(personaServicio, times(1)).AgregarPersona(persona);
    }
}
