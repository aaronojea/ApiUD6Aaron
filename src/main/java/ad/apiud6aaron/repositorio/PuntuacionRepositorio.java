package ad.apiud6aaron.repositorio;

import ad.apiud6aaron.modelo.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntuacionRepositorio extends JpaRepository<Puntuacion, Long> {

    List<Puntuacion> findByJuegoId(Long id);

    List<Puntuacion> findByJuegoIdOrderByPuntuacionDesc(Long id);
}
