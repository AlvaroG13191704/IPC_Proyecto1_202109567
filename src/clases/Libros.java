
package clases;

public class Libros {
    private Long ID;
    private String titulo;
    private String autor;
    private String tipo;
    private Long copias;
    private Long disponibles;
    private Long ocupados;
    

    public Libros(Long ID, String titulo, String autor, String tipo, Long copias, Long disponibles, Long ocupados) {
        this.ID = ID;
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.copias = copias;
        this.disponibles = disponibles;
        this.ocupados = ocupados;
        
    }
    
    
 
    public Libros(Long ID, String titulo, String autor, String tipo, Long copias) {
        this.ID = ID;
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.copias = copias;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Libros{" + "ID=" + ID + ", titulo=" + titulo + ", autor=" + autor + ", tipo=" + tipo + ", copias=" + copias + ", disponibles=" + disponibles + ", ocupados=" + ocupados +"}";
    }

    public Long getCopias() {
        return copias;
    }

    public void setCopias(Long copias) {
        this.copias = copias;
    }

    public Long getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(Long disponibles) {
        this.disponibles = disponibles;
    }

    public Long getOcupados() {
        return ocupados;
    }

    public void setOcupados(Long ocupados) {
        this.ocupados = ocupados;
    }


    
    
}
