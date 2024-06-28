package com.raccoonsden.raccoonsden.servicios;

import com.raccoonsden.raccoonsden.entidades.Curso;
import com.raccoonsden.raccoonsden.repositorios.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServicio {
    @Autowired
    CursoRepositorio cursoRepositorio;
    @Transactional
    public void crearCurso(String titulo, String categoria) {
        Curso curso = new Curso();
        curso.setTitulo(titulo);
        curso.setCategoria(categoria);

        cursoRepositorio.save(curso);
    }
    @Transactional
    public void modificarCurso(Long id, String categoria){
        Optional<Curso> curso = cursoRepositorio.findById(id);
        if(curso.isPresent()){
            curso.get().setCategoria(categoria);
            cursoRepositorio.save(curso.get());
        }
    }
    @Transactional
    public void eliminarCurso(Long id) {
        Optional<Curso> curso = cursoRepositorio.findById(id);
        if (curso.isPresent()) {
            cursoRepositorio.deleteById(id);
        }
    }
    public List<Curso> obtenerCursos(){
        List<Curso> cursos = cursoRepositorio.findAll();
        return cursos;
    }

}
