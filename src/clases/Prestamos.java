package clases;

public class Prestamos {

    //Atributos que hacen referencia a otras clases
    private String usuarioID;
    private String libroID;
    private String fechaEntrega;
    private String status;
    
    public Prestamos(String usuarioID, String libroID, String fechaEntrega, String status) {
        this.usuarioID = usuarioID;
        this.libroID = libroID;
        this.fechaEntrega = fechaEntrega;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    


    

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getLibroID() {
        return libroID;
    }

    public void setLibroID(String libroID) {
        this.libroID = libroID;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

}
