
package entities;

public class Libro {
    
    //ATRIBUTOS
    private String nombre;
    private String isbn;
    private double precioBase;
    private int unidadesDisponibles;
    private int numeroImagenes;
    private int numeroVideos;
    
    //METODOS
    //CONSTRUCTORES

    public Libro() {
    }

    public Libro(String nombre, String isbn, double precioBase, int unidadesDisponibles, int numeroImagenes, int numeroVideos) {
        this.nombre = nombre;
        this.isbn = isbn;
        this.precioBase = precioBase;
        this.unidadesDisponibles = unidadesDisponibles;
        this.numeroImagenes = numeroImagenes;
        this.numeroVideos = numeroVideos;
    }

    //GETTERS

    public String getNombre() {
        return nombre;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public int getNumeroImagenes() {
        return numeroImagenes;
    }

    public int getNumeroVideos() {
        return numeroVideos;
    }
    
    //SETTERS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public void setNumeroImagenes(int numeroImagenes) {
        this.numeroImagenes = numeroImagenes;
    }

    public void setNumeroVideos(int numeroVideos) {
        this.numeroVideos = numeroVideos;
    }
    
    //TOSTRING

    @Override
    public String toString() {
        return "Libro{" + "nombre=" + nombre + ", isbn=" + isbn + ", precioBase=" + precioBase + ", unidadesDisponibles=" + unidadesDisponibles + ", numeroImagenes=" + numeroImagenes + ", numeroVideos=" + numeroVideos + '}';
    }
}
