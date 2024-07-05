package com.aluracursos.forohub.dominio.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITopicoRepository extends JpaRepository<Topico, Long>{
    Page<Topico> findAllByOrderByFechaCreacionDesc(Pageable paginacion);
    Optional<Topico> findByTitulo(String titulo);
    Optional<Topico> findByMensaje(String mensaje);
}
