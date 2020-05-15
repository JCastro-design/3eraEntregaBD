
package entities;

public class Linea {
    
    //ATRIBUTOS
    private int cantidad;
    //RELACIONES
    private Prestamo prestamo;
    private Libro libroEnPrestamo;
    
    //METODOS
    //CONSTRUCTORES

    public Linea() {
    }

    public Linea(int cantidad, Prestamo prestamo, Libro libroEnPrestamo) {
        this.cantidad = cantidad;
        this.prestamo = prestamo;
        this.libroEnPrestamo = libroEnPrestamo;
    }
    
    //GETTERS

    public int getCantidad() {
        return cantidad;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public Libro getLibroEnPrestamo() {
        return libroEnPrestamo;
    }
    
    //SETTERS

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public void setLibroEnPrestamo(Libro libroEnPrestamo) {
        this.libroEnPrestamo = libroEnPrestamo;
    }
    
    //TOSTRING

    @Override
    public String toString() {
        return "Linea{" + "cantidad=" + cantidad + ", prestamo=" + prestamo + ", libroEnPrestamo=" + libroEnPrestamo + '}';
    } 
}
