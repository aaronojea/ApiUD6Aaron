package ad.apiud6aaron.modelo;

/*
 * Clase: ControladorPruebas
 * Autor: Aarón Ojea Olmos
 * Fecha de creación: 2024
 * Descripción-Enunciado: Clase modelo de la tabla juego para mapearla con la BD.
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "juegos")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    String nombre;

    public Juego() {}

    public Juego(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
