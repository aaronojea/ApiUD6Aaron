package ad.apiud6aaron.repositorio;

/*
 * Clase: ControladorPruebas
 * Autor: Aarón Ojea Olmos
 * Fecha de creación: 2024
 * Descripción-Enunciado: Clase repositorio de puntuacion, consiste en una interface que ofrece operaciones para interactuar con una BD.
 */

import ad.apiud6aaron.modelo.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntuacionRepositorio extends JpaRepository<Puntuacion, Long> {

    List<Puntuacion> findByJuegoId(Long id);

    List<Puntuacion> findByJuegoIdOrderByPuntuacionDesc(Long id);

    Puntuacion findByNombreLike(String nombreJugador);
}
