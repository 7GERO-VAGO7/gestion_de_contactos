package com.contactos.contactos.entidad;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;

@Entity
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private String numero;

    private String correo;

    private Date fechaCumpleano;

    public Contacto() {
    }

    public Contacto(Long id, Persona persona, String numero, String correo, Date fechaCumpleano) {
        this.id = id;
        this.persona = persona;
        this.numero = numero;
        this.correo = correo;
        this.fechaCumpleano = fechaCumpleano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaCumpleano() {
        return fechaCumpleano;
    }

    public void setFechaCumpleano(Date fechaCumpleano) {
        this.fechaCumpleano = fechaCumpleano;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", persona=" + persona +
                ", numero='" + numero + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaCumpleano=" + fechaCumpleano +
                '}';
    }
}
