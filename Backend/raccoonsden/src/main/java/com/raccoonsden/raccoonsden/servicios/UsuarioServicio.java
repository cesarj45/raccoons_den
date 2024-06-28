package com.raccoonsden.raccoonsden.servicios;

import com.raccoonsden.raccoonsden.entidades.Usuario;
import com.raccoonsden.raccoonsden.excepciones.MiException;
import com.raccoonsden.raccoonsden.repositorios.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearUsuario(String nombre, String email, String password, String tipo) throws MiException {

        validar(nombre, email, password, tipo);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTipo(tipo);

        usuarioRepositorio.save(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios;
    }
    @Transactional
    public void modificarUsuario(Long id, String nombre, String email, String password, String tipo) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioActual = usuario.get();
            usuarioActual.setNombre(nombre);
            usuarioActual.setEmail(email);
            usuarioActual.setPassword(password);
            usuarioActual.setTipo(tipo);
            usuarioRepositorio.save(usuarioActual);
        }
    }
    @Transactional
    public void CambiarPassword(Long id, String password) {
        //validarPassword **PENDIENTE**
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioActual = usuario.get();
            usuarioActual.setPassword(password);
            usuarioRepositorio.save(usuarioActual);
        }
    }
    @Transactional
    public void eliminarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if (usuario.isPresent()) {
            usuarioRepositorio.delete(usuario.get());
        }
    }

    public void validar(String nombre, String email, String password, String tipo) throws MiException {
        if(nombre.isEmpty() || nombre == null){
            throw new MiException("Nombre vacio.");
        }
        if(email.isEmpty() || email == null)
        {
            throw new MiException("Correo electronico vacio.");
        }
        if(password.isEmpty() || password == null){
            throw new MiException("Password vacio.");
        }
        if(tipo.isEmpty() || tipo == null){
            throw new MiException("Tipo vacio.");
        }

    }
}
