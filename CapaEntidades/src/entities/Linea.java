
package entities;

public class Linea {
    
    //ATRIBUTOS
    private int cantidad;
    private double valorTotalLibro;
    private double subtotal;
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

    public Linea(int cantidad, double valorTotalLibro, double subtotal, Prestamo prestamo, Libro libroEnPrestamo) {
        this.cantidad = cantidad;
        this.valorTotalLibro = valorTotalLibro;
        this.subtotal = subtotal;
        this.prestamo = prestamo;
        this.libroEnPrestamo = libroEnPrestamo;
    }
    
    //GETTERS

    public int getCantidad() {
        return cantidad;
    }

    public double getValorTotalLibro() {
        return valorTotalLibro;
    }

    public double getSubtotal() {
        return subtotal;
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

    public void setValorTotalLibro(double valorTotalLibro) {
        this.valorTotalLibro = valorTotalLibro;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
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
        return "Linea{" + "cantidad=" + cantidad + ", valorTotalLibro=" + valorTotalLibro + ", subtotal=" + subtotal + ", prestamo=" + prestamo + ", libroEnPrestamo=" + libroEnPrestamo + '}';
    }
}
