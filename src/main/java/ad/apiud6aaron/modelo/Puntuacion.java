package ad.apiud6aaron.modelo;

import jakarta.persistence.*;

@Entity(name = "puntuaciones")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String nombre;
    long puntuacion;
    @ManyToOne
    @JoinColumn(name = "id_juego")
    private Juego juego;

    public Puntuacion() {}

    public Puntuacion(long id, String nombre, long puntuacion, Juego juego) {
        this.id = id;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.juego = juego;
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

    public long getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(long puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
}
