
package entities;

import java.time.LocalDateTime;
import java.util.List;


public class Prestamo {
    
    //ATRIBUTOS
    private LocalDateTime fecha;
    private int numero;
    //RELACIONES
    private List<Linea> lineas;
    private List<Moneda> pagoMonedas;
    
    //METODOS
    //CONSTRUCTORES

    public Prestamo() {
    }

    public Prestamo(LocalDateTime fecha, int numero, List<Linea> lineas, List<Moneda> pagoMonedas) {
        this.fecha = fecha;
        this.numero = numero;
        this.lineas = lineas;
        this.pagoMonedas = pagoMonedas;
    }

    //GETTERS

    public LocalDateTime getFecha() {
        return fecha;
    }

    public int getNumero() {
        return numero;
    }

    public List<Linea> getLineas() {
        return lineas;
    }

    public List<Moneda> getPagoMonedas() {
        return pagoMonedas;
    }

    //SETTERS

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }

    public void setPagoMonedas(List<Moneda> pagoMonedas) {
        this.pagoMonedas = pagoMonedas;
    }
    
    //TOSTRING

    @Override
    public String toString() {
        return "Prestamo{" + "fecha=" + fecha + ", numero=" + numero + ", lineas=" + lineas + ", pagoMonedas=" + pagoMonedas + '}';
    }
}
