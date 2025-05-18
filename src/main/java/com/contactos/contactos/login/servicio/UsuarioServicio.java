package com.contactos.contactos.login.servicio;

import com.contactos.contactos.login.entidad.Usuario;
import com.contactos.contactos.login.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio repositorio;
    public Usuario login (String correo, String contrasena){
        List<Usuario> listUser = buscarPorCorreo(correo);

        if (!listUser.isEmpty()){
            Usuario usuario = listUser.get(0);
            if (usuario.getContrasena().equals(contrasena)){
                return usuario;
            }
        }
        return null;
    }


    private List<Usuario> buscarPorCorreo(String correo) {
        List<Usuario> resultado = List.of();
        List<Usuario> allUser = repositorio.findAll();
        for (Usuario usuario:allUser){
            if (usuario.getCorreo().equals(correo)) {

            return List.of(usuario);
            }

        }
        return resultado;
    }
}
