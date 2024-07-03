package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.dominio.topico.DatosRegistroTopico;
import com.aluracursos.forohub.dominio.topico.ITopicoRepository;
import com.aluracursos.forohub.dominio.topico.Topico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
