package com.raccoonsden.raccoonsden.repositorios;

import com.raccoonsden.raccoonsden.entidades.Usuario;
import com.raccoonsden.raccoonsden.enumeraciones.TipoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    public List<Usuario> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE u.tipo = :tipo")
    public Usuario buscarPorTipo(@Param("tipo")TipoUser tipoUser);
}
