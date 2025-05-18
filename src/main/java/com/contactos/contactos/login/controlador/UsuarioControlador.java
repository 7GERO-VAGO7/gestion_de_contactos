package com.contactos.contactos.login.controlador;

import com.contactos.contactos.login.entidad.Usuario;
import com.contactos.contactos.login.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        Usuario users = usuarioServicio.login(usuario.getCorreo(),usuario.getContrasena());

        if (users != null){
            return ResponseEntity.ok(usuario);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
