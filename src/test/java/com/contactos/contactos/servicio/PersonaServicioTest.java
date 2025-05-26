package com.contactos.contactos.servicio;

import com.contactos.contactos.entidad.Persona;
import com.contactos.contactos.repositorio.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaServicioTest {

    @InjectMocks
    private PersonaServicio personaServicio;

    @Mock
    private PersonaRepository personaRepository;

    @Test
    void testAgregarPersona() {
        Persona persona = new Persona();
        persona.setNombre("Sofía");

        when(personaRepository.save(persona)).thenReturn(persona);

        Persona resultado = personaServicio.AgregarPersona(persona);

        assertNotNull(resultado);
        assertEquals("Sofía", resultado.getNombre());
        verify(personaRepository, times(1)).save(persona);
    }
}
