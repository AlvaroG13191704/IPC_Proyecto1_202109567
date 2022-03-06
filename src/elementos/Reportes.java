
package elementos;

public class Reportes {
   private String fechaGeneracion;
   private String usuario;
   private String tipoReporte;

    public Reportes(String fechaGeneracion, String usuario, String tipoReporte) {
        this.fechaGeneracion = fechaGeneracion;
        this.usuario = usuario;
        this.tipoReporte = tipoReporte;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
   
   
}
