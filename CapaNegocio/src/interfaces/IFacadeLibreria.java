
package interfaces;

import entities.Denominacion;
import entities.DtoResumen;
import entities.Libro;
import entities.Linea;

/*EN LAS INTERFACES SOLO VAN LAS DEFINICIONES DE LOS METODOS,
  PUES LA IMPLEMENTACION DE CADA METODO VA EN LAS FACHADAS
  QUE IMPLEMENTAN CADA INTERFAZ*/

public interface IFacadeLibreria {
    public boolean crearNuevoPrestamo(); 
    public DtoResumen agregarLinea(Libro libro, int cantidad);
    public DtoResumen eliminarLinea(Linea linea);
    public DtoResumen introducirMoneda(Denominacion denominacion, int cantidad);
    public DtoResumen terminarPrestamo();
    public DtoResumen consultarPrestamo(int numeroPrestamo);
}
