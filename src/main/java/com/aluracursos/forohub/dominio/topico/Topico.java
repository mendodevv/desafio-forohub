package com.aluracursos.forohub.dominio.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "topicos")
@Entity(name = "Topico")

// Anotaciones de Lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;

    @Column(name = "fecha")
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    private String curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
        this.status = Status.valueOf("SIN_RESPUESTA");
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizarTopico(DatosActualizarTopico datosRegistroTopico) {
        if (datosRegistroTopico.titulo() != null) {
            this.titulo = datosRegistroTopico.titulo();
        }

        if (datosRegistroTopico.mensaje() != null) {
            this.mensaje = datosRegistroTopico.mensaje();
        }

        if (datosRegistroTopico.autor() != null) {
            this.autor = datosRegistroTopico.autor();
        }

        if (datosRegistroTopico.curso() != null) {
            this.curso = datosRegistroTopico.curso();
        }
    }
}
