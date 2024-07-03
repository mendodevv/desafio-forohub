package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.dominio.topico.DatosRegistroTopico;
import com.aluracursos.forohub.dominio.topico.DatosRespuestaTopico;
import com.aluracursos.forohub.dominio.topico.ITopicoRepository;
import com.aluracursos.forohub.dominio.topico.Topico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRegistroTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        return ResponseEntity.ok(new DatosRegistroTopico(topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso()));
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> mostrarTopicos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(topico -> new DatosRespuestaTopico(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getAutor(),
                        topico.getCurso())));
    }
}
