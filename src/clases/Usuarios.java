
package clases;

public class Usuarios {
    private Long iD;
    private String usuario;
    private String password;
    private String facultad;
    private String carrera;
    private Long tipo; //Tipo 1 será administrador, Tipo 2 será usuario

    public Usuarios(Long iD, String usuario, String password, String facultad, String carrera, Long tipo) {
        this.iD = iD;
        this.usuario = usuario;
        this.password = password;
        this.facultad = facultad;
        this.carrera = carrera;
        this.tipo = tipo;
    }

    public Usuarios(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
     public Usuarios(Long iD,String usuario, String password) {
        this.iD = iD;
        this.usuario = usuario;
        this.password = password;
    }

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "iD=" + iD + ", usuario=" + usuario + ", password=" + password + ", facultad=" + facultad + ", carrera=" + carrera + ", tipo=" + tipo + '}';
    }
    
    
    
}
