package com.raccoonsden.raccoonsden.controladores;

import com.raccoonsden.raccoonsden.entidades.Usuario;
import com.raccoonsden.raccoonsden.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/registro")
    public String registrar(){
        return "registro_form.html";
    }

    @PostMapping("/registrar")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
                           @RequestParam String password2, ModelMap modelo){
        try {
            usuarioServicio.crearUsuario(nombre,email, password, password2);
            modelo.put("exito", "Usuario registrado correctamente");
        }catch (Exception e){
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
        }
        return "registro_form.html";
    }


    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        if(error != null){
            modelo.put("error", "Usuario o contraseña invalidos!");
        }
        return "login.html";
    }
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session) {
        //recuperamos el usuario de la session para ver si es admin
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        if(logueado.getTipo().toString().equals("ADMIN")){
            return "redirect:/admin/dashboard";
        }
    return "inicio.html";
    }

}
