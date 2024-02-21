package ad.apiud6aaron.controlador;

import ad.apiud6aaron.modelo.Juego;
import ad.apiud6aaron.repositorio.JuegoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    //Crea un juego a la BD mediante peticion web POST
    public Juego crearJuego(@RequestBody Juego juego) {
        return juegoRepositorio.save(juego);
    }
}
