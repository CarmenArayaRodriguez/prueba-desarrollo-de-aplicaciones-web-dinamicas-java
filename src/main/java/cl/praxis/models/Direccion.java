package cl.praxis.models;

public class Direccion {
    private String nombre;
    private int numeracion;
    private int usuarioId;

    public Direccion(String nombre, int numeracion, int usuarioId) {
        this.nombre = nombre;
        this.numeracion = numeracion;
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
