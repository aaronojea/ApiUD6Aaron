package ad.apiud6aaron.repositorio;

/*
 * Clase: ControladorPruebas
 * Autor: Aarón Ojea Olmos
 * Fecha de creación: 2024
 * Descripción-Enunciado: Clase repositorio de juego, consiste en una interface que ofrece operaciones para interactuar con una BD.
 */

import ad.apiud6aaron.modelo.Juego;
import ad.apiud6aaron.modelo.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuegoRepositorio extends JpaRepository<Juego, Long> {

    Juego findByNombreLike(String nombreJuego);
}
