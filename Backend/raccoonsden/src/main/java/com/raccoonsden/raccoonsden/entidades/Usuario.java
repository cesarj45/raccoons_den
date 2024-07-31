package com.raccoonsden.raccoonsden.entidades;

import com.raccoonsden.raccoonsden.enumeraciones.TipoUser;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(generator = "uuid")
    private Long id;
    private String nombre;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private TipoUser tipo;

    public Usuario(){}

    public TipoUser getTipo() {
        return tipo;
    }

    public void setTipo(TipoUser tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
