
package entities;

public class Moneda {
    //ATRIBUTOS
    //RELACIONES
    private Denominacion denominacion;
    
    //METODOS
    //CONSTRUCTORES

    public Moneda() {
    }

    public Moneda(Denominacion denominacion) {
        this.denominacion = denominacion;
    }
    
    
    //GETTERS

    public Denominacion getDenominacion() {
        return denominacion;
    }
    
    //SETTERS

    public void setDenominacion(Denominacion denominacion) {
        this.denominacion = denominacion;
    }
    
    //TOSTRING

    @Override
    public String toString() {
        return "Moneda{" + "denominacion=" + denominacion + '}';
    }
}
