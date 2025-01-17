package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.dominio.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        Optional<Topico> topicoExistente = topicoRepository.findByTitulo(datosRegistroTopico.titulo());
        if (topicoExistente.isPresent()) {
            return ResponseEntity.badRequest().body("Ese título ya existe, ingresa otro por favor");
        }

        Optional<Topico> topicoExistentePorMensaje = topicoRepository.findByMensaje(datosRegistroTopico.mensaje());
        if (topicoExistentePorMensaje.isPresent()) {
            return ResponseEntity.badRequest().body("Ese mensaje ya existe, ingresa otro por favor");
        }

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        return ResponseEntity.ok(new DatosRegistroTopico(topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso()));
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> mostrarTopicos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAllByOrderByFechaCreacionDesc(paginacion).map(topico -> new DatosRespuestaTopico(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getAutor(),
                        topico.getCurso())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> topicoIndividual(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> ResponseEntity.ok(new DatosRespuestaTopico(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getAutor(),
                        topico.getCurso())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }

        topico.actualizarTopico(datosActualizarTopico);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor(), topico.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().body("El tópico fue eliminado con éxito");
        } else {
            return  ResponseEntity.notFound().build();
        }
    }
}
