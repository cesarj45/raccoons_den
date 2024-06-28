package com.raccoonsden.raccoonsden.repositorios;

import com.raccoonsden.raccoonsden.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {
}
