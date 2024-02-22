package ad.apiud6aaron.repositorio;

import ad.apiud6aaron.modelo.Juego;
import ad.apiud6aaron.modelo.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuegoRepositorio extends JpaRepository<Juego, Long> {
}
