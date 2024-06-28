package com.raccoonsden.raccoonsden.controladores;

import com.raccoonsden.raccoonsden.excepciones.MiException;
import com.raccoonsden.raccoonsden.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/usuario")  //localhost:8080/usuario
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registrar")     //localhost:8080/usuario/registrar
    public String registrar(){
        return "registro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,@RequestParam String email, @RequestParam String password, ModelMap model){
        try {
            usuarioServicio.crearUsuario(nombre,email,password,"Usuario");
            model.put("exito","Usuario registrado exitosamente");
        }catch (MiException ex){
            model.put("error",ex.getMessage());
            return "registro_form.html";
        }
        return "index.html";
    }
}
