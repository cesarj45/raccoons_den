package com.raccoonsden.raccoonsden.repositorios;

import com.raccoonsden.raccoonsden.entidades.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c WHERE c.titulo = :titulo")
    public List<Curso> buscarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT c FROM Curso c WHERE c.categoria = :categoria")
    public List<Curso> buscarPorCategoria(@Param("categoria") String categoria);

    @Query("SELECT c FROM Curso c WHERE c.id = :id")
    public Curso buscarPorID(@Param("id") Long id);
}
