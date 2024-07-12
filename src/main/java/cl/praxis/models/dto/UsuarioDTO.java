package cl.praxis.models.dto;

public class UsuarioDTO {
    private String nombre;
    private String correo;
    private String password;
    private String nick;
    private int peso;
    private String direccionNombre;
    private int direccionNumeracion;
    private int rolId;

    public UsuarioDTO(String nombre, String correo, String password, String nick, int peso, String direccionNombre, int direccionNumeracion, int rolId) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.nick = nick;
        this.peso = peso;
        this.direccionNombre = direccionNombre;
        this.direccionNumeracion = direccionNumeracion;
        this.rolId = rolId;
    }

    public UsuarioDTO(String nombre, String correo, String password, String nick, int peso) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.nick = nick;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getDireccionNombre() {
        return direccionNombre;
    }

    public void setDireccionNombre(String direccionNombre) {
        this.direccionNombre = direccionNombre;
    }

    public int getDireccionNumeracion() {
        return direccionNumeracion;
    }

    public void setDireccionNumeracion(int direccionNumeracion) {
        this.direccionNumeracion = direccionNumeracion;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }
}
