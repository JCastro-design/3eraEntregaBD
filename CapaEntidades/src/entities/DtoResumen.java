
package entities;

public class DtoResumen { //Clase que se usa para devolver datos desde varios metodos de FacadeLibreria
    
    //ATRIBUTOS
    private String mensaje; //Para desplegar mensajes de error
    private boolean tuvoExito; //Indica si se pudo agregar la linea de prestamo
    private double total; //Es la suma del subtotal de todas las lineas del prestamo
    private double saldoEnMonedas; //Monto para pagar
    private double vueltas; //saldoEnMonedas-total
    //RELACIONES
    private Prestamo prestamo;

    //METODOS
    //CONSTRUCTORES

    public DtoResumen() {
    }

    public DtoResumen(String mensaje, boolean tuvoExito, double total, double saldoEnMonedas, double vueltas, Prestamo prestamo) {
        this.mensaje = mensaje;
        this.tuvoExito = tuvoExito;
        this.total = total;
        this.saldoEnMonedas = saldoEnMonedas;
        this.vueltas = vueltas;
        this.prestamo = prestamo;
    }
    
    //GETTERS

    public String getMensaje() {
        return mensaje;
    }

    public boolean isTuvoExito() {
        return tuvoExito;
    }

    public double getTotal() {
        return total;
    }

    public double getSaldoEnMonedas() {
        return saldoEnMonedas;
    }

    public double getVueltas() {
        return vueltas;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }
    
    //SETTERS

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setTuvoExito(boolean tuvoExito) {
        this.tuvoExito = tuvoExito;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setSaldoEnMonedas(double saldoEnMonedas) {
        this.saldoEnMonedas = saldoEnMonedas;
    }

    public void setVueltas(double vueltas) {
        this.vueltas = vueltas;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    
    //TOSTRING

    @Override
    public String toString() {
        return "DtoResumen{" + "mensaje=" + mensaje + ", tuvoExito=" + tuvoExito + ", total=" + total + ", saldoEnMonedas=" + saldoEnMonedas + ", vueltas=" + vueltas + ", prestamo=" + prestamo + '}';
    }  
}
