package ad.apiud6aaron.controlador;

import ad.apiud6aaron.modelo.Juego;
import ad.apiud6aaron.modelo.Puntuacion;
import ad.apiud6aaron.repositorio.PuntuacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/puntuaciones")
public class PuntuacionControlador {

    @Autowired
    private PuntuacionRepositorio puntuacionRepositorio;

    //Devuelve una lista con todos los jugadores y sus puntuaciones de la BD.
    @GetMapping
    public List<Puntuacion> obtenerPuntuaciones() {
        return puntuacionRepositorio.findAll();
    }

    //Devuelve una lista con los jugadores ordenados alfabéticamente. (Por defecto los muestra por orden en que se han añadido a la BD).
    @GetMapping("/alfabetico")
    public List<Puntuacion> obtenerNombreOrdenados() {
        return puntuacionRepositorio.findAll(Sort.by(Sort.Direction.ASC,"nombre"));
    }

    //Devuelve una lista con las puntuaciones ordenadas de mayor a menor.
    @GetMapping("/mayor")
    public List<Puntuacion> obtenerPuntuacionesOrdendasMayor() {
        return puntuacionRepositorio.findAll(Sort.by(Sort.Direction.DESC, "puntuacion"));
    }

    //Devuelve una lista con las puntuaciones ordenadas de menor a mayor.
    @GetMapping("/menor")
    public List<Puntuacion> obtenerPuntuacionesOrdendasMenor() {
        return puntuacionRepositorio.findAll(Sort.by(Sort.Direction.ASC, "puntuacion"));
    }

    //Obtener un jugador con su puntuacion por su ID
    @GetMapping("/{id}")
    public Puntuacion obtenerPuntuacionPorId(@PathVariable Long id) {
        Optional<Puntuacion> resultado = puntuacionRepositorio.findById(id);
        return resultado.orElseThrow(() -> new RuntimeException("ERROR, Puntuacion no encontrada"));
    }

    //Crea un jugador con su puntuacion en la BD mediante una peticion web POST
    @PostMapping
    public Puntuacion crearJugador(@RequestBody Puntuacion puntuacion) {
        return puntuacionRepositorio.save(puntuacion);
    }

    //Edita un jugador, que se pasa por parametro con su id, de la base de datos mediante una peticion web PUT en la que se añade a la ruta el id del jugador
    @PutMapping("/{id}")
    public Puntuacion editarPuntuacion(@PathVariable Long id, @RequestBody Puntuacion puntuacion) {
        return puntuacionRepositorio.findById(id).map(puntuacionTemp -> {
            puntuacionTemp.setNombre(!puntuacion.getNombre().isEmpty()? puntuacion.getNombre(): puntuacionTemp.getNombre());
            puntuacionTemp.setPuntuacion(puntuacion.getPuntuacion() < 0? puntuacion.getPuntuacion(): puntuacionTemp.getPuntuacion());
            return puntuacionRepositorio.save(puntuacionTemp);
        }).orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    //Elimina un juego de la BD mediante una peticion web DELETE
    @DeleteMapping("/{id}")
    public void eliminarPuntuacion(@PathVariable Long id) {
        puntuacionRepositorio.deleteById(id);
    }
}
