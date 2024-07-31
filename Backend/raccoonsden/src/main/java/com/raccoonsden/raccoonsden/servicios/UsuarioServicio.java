package com.raccoonsden.raccoonsden.servicios;

import com.raccoonsden.raccoonsden.entidades.Usuario;
import com.raccoonsden.raccoonsden.enumeraciones.TipoUser;
import com.raccoonsden.raccoonsden.excepciones.MiException;
import com.raccoonsden.raccoonsden.repositorios.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearUsuario(String nombre, String email, String password, String password2) throws MiException {

        validar(nombre, email, password, password2);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setTipo(TipoUser.USER);

        usuarioRepositorio.save(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios;
    }
    @Transactional
    public void modificarUsuario(Long id, String nombre, String email, String password, String password2) throws MiException {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);

        if (usuario.isPresent()) {
            validar(nombre, email, password, password2);
            Usuario usuarioActual = usuario.get();
            usuarioActual.setNombre(nombre);
            usuarioActual.setEmail(email);
            usuarioActual.setPassword(new BCryptPasswordEncoder().encode(password));
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

    public void validar(String nombre, String email, String password, String password2) throws MiException {
        if(nombre == null || nombre.isEmpty()){
            throw new MiException("El nomnbre Nombre no puede ser nulo o estar vacío.");
        }
        if(email == null || email.isEmpty())
        {
            throw new MiException("El email no puede ser nulo o estar vacío.");
        }
        if(password == null || password.length() < 8){
            throw new MiException("La contraseña no puede estar vacia o tener menos de 8 digitos.");
        }
        if(!password.equals(password2)){
            throw new MiException("Las contraseñas no coinciden.");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getTipo().toString());
            permisos.add(p);
            //Recuperamos la sesion
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            //le ingresamos el usuario recuperado de la Base de Datos a la session con la clave usuariosession
            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(),permisos);

        }else return null;
    }
}
