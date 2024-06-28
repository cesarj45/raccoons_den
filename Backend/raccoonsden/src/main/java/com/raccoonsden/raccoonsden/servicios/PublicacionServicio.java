package com.raccoonsden.raccoonsden.servicios;

import com.raccoonsden.raccoonsden.entidades.Publicacion;
import com.raccoonsden.raccoonsden.entidades.Usuario;
import com.raccoonsden.raccoonsden.repositorios.PublicacionRepositorio;
import com.raccoonsden.raccoonsden.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PublicacionServicio {
    @Autowired
    PublicacionRepositorio publicacionRepositorio;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Transactional
    public void crearPublicacion(String titulo, String contenido, Date fechaPublicacion, Long idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario).get();
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(titulo);
        publicacion.setContenido(contenido);
        publicacion.setFechaPublicacion(fechaPublicacion);
        publicacion.setUsuario(usuario);
        publicacionRepositorio.save(publicacion);

    }

    public List<Publicacion> obtenerPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepositorio.findAll();
        return publicaciones;
    }
}
