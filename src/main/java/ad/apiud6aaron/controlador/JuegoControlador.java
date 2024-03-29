package ad.apiud6aaron.controlador;

/*
 * Clase: ControladorPruebas
 * Autor: Aarón Ojea Olmos
 * Fecha de creación: 2024
 * Descripción-Enunciado: Clase controlador de juego, donde se realizan los métodos CRUD y otros métodos que se deseen.
 */

import ad.apiud6aaron.modelo.Juego;
import ad.apiud6aaron.repositorio.JuegoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/juegos")
public class JuegoControlador {

    @Autowired
    private JuegoRepositorio juegoRepositorio;

    //Devuelve una lista con todos los juegos de la BD.
    @GetMapping
    public List<Juego> obtenerJuegos() {
        return juegoRepositorio.findAll();
    }

    //Devuelve una lista con todos los juegos ordenados alfabéticamente. (Por defecto los muestra por orden en que se han añadido a la BD).
    @GetMapping("/alfabetico")
    public List<Juego> obtenerJuegosOrdenados() {
        return juegoRepositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    //Obtener un juego por su ID
    @GetMapping("/{id}")
    public Juego obtenerJuegoPorId(@PathVariable Long id) {
        Optional<Juego> resultado = juegoRepositorio.findById(id);
        return resultado.orElseThrow(() -> new RuntimeException("ERROR, Juego no encontrado"));
    }

    //Obteber un juego por su nombre
    @GetMapping("/juego/{nombreJuego}")
    public Juego obtenerJuegoPorNombre(@PathVariable String nombreJuego) {
        return juegoRepositorio.findByNombreLike(nombreJuego);
    }

    //Crea un juego en la BD mediante una peticion web POST
    @PostMapping
    public Juego crearJuego(@RequestBody Juego juego) {
        return juegoRepositorio.save(juego);

    }

    //Edita un juego, que se pasa por parametro con su id, de la base de datos mediante una peticion web PUT en la que se añade a la ruta el id del juego
    @PutMapping("/{id}")
    public Juego editarJuego(@PathVariable Long id, @RequestBody Juego juego) {
        return juegoRepositorio.findById(id).map(juegoTemp -> {
            juegoTemp.setNombre((!juego.getNombre().isEmpty())? juego.getNombre(): juegoTemp.getNombre());
            return juegoRepositorio.save(juegoTemp);
        }).orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }

    //Elimina un juego de la BD mediante una peticion web DELETE
    @DeleteMapping("/{id}")
    public void eliminarJuego(@PathVariable Long id) {
        juegoRepositorio.deleteById(id);
    }
}
