package ad.apiud6aaron.controlador;

/*
 * Clase: ControladorPruebas
 * Autor: Aarón Ojea Olmos
 * Fecha de creación: 2024
 * Descripción-Enunciado: Clase controlador de puntuacion, donde se realizan los métodos CRUD y otros métodos que se deseen.
 */

import ad.apiud6aaron.modelo.Puntuacion;
import ad.apiud6aaron.repositorio.JuegoRepositorio;
import ad.apiud6aaron.repositorio.PuntuacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/puntuaciones")
public class PuntuacionControlador {

    @Autowired
    private PuntuacionRepositorio puntuacionRepositorio;
    @Autowired
    private JuegoRepositorio juegoRepositorio;

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
    @GetMapping("/juego/mayor/{idJuego}")
    public List<Puntuacion> obtenerPuntuacionesOrdendasMayor(@PathVariable Long idJuego) {
        return puntuacionRepositorio.findByJuegoIdOrderByPuntuacionDesc(idJuego);
    }

    //Obtener un jugador con su puntuacion por su ID
    @GetMapping("/{id}")
    public Puntuacion obtenerPuntuacionPorId(@PathVariable Long id) {
        Optional<Puntuacion> resultado = puntuacionRepositorio.findById(id);
        return resultado.orElseThrow(() -> new RuntimeException("ERROR, Puntuacion no encontrada"));
    }

    //Devuelve una lista de las puntuaciones de ese juego
    @GetMapping("/juego/{idJuego}")
    public List<Puntuacion> obtenerPuntuacionesJuego(@PathVariable Long idJuego) {
        return puntuacionRepositorio.findByJuegoId(idJuego);
    }

    //Obtener un jugador con su puntuacion por su nombre
    @GetMapping("/jugador/{nombreJugador}")
    public Puntuacion obtenerPuntuacionPorNombre(@PathVariable String nombreJugador) {
        return puntuacionRepositorio.findByNombreLike(nombreJugador);
    }

    //Crea un jugador con su puntuacion en la BD mediante una peticion web POST
    @PostMapping("/juego/{id}")
    public Puntuacion crearJugador(@PathVariable Long id, @RequestBody Puntuacion puntuacion) {
        Puntuacion put = juegoRepositorio.findById(id).map(juego -> {
            puntuacion.setJuego(juego);
            return puntuacionRepositorio.save(puntuacion);
        }).orElseThrow(() -> new RuntimeException("Juego no encontrado"));
        return put;
    }

    //Edita un jugador, que se pasa por parametro con su id, de la base de datos mediante una peticion web PUT en la que se añade a la ruta el id del jugador
    @PutMapping("/{id}")
    public Puntuacion editarPuntuacion(@PathVariable Long id, @RequestBody Puntuacion puntuacion) {
        return puntuacionRepositorio.findById(id).map(puntuacionTemp -> {
            puntuacionTemp.setNombre(!puntuacion.getNombre().isEmpty()? puntuacion.getNombre(): puntuacionTemp.getNombre());
            puntuacionTemp.setPuntuacion((puntuacion.getPuntuacion() > 0)? puntuacion.getPuntuacion(): puntuacionTemp.getPuntuacion());
            return puntuacionRepositorio.save(puntuacionTemp);
        }).orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    //Elimina una puntuacion de la BD mediante una peticion web DELETE
    @DeleteMapping("/{id}")
    public void eliminarPuntuacion(@PathVariable Long id) {
        puntuacionRepositorio.deleteById(id);
    }
}
