
package interfaces;

import accesoDatos.RepositorioLibro;
import accesoDatos.RepositorioPrestamo;
import entities.Denominacion;
import entities.DtoResumen;
import entities.Libro;
import entities.Linea;
import entities.Prestamo;
import facades.FacadeLibreria;
import java.util.List;

/*EN LAS INTERFACES SOLO VAN LAS DEFINICIONES DE LOS METODOS,
  PUES LA IMPLEMENTACION DE CADA METODO VA EN LAS FACHADAS
  QUE IMPLEMENTAN CADA INTERFAZ*/

public interface IFacadeLibreria {
    public List<Libro> getCatalogo();
    public List<Prestamo> getPrestamos();
    public Prestamo getPrestamoActual();
    public RepositorioLibro getGestionLibro();
    public RepositorioPrestamo getGestionPrestamo();
    public void setCatalogo(List<Libro> catalogo);
    public void setPrestamos(List<Prestamo> prestamos);
    public void setPrestamoActual(Prestamo prestamoActual);
    public void setGestionLibro(RepositorioLibro gestionLibro);
    public void setGestionPrestamo(RepositorioPrestamo gestionPrestamo);
    public boolean crearNuevoPrestamo(); 
    public DtoResumen agregarLinea(Libro libro, int cantidad);
    public DtoResumen eliminarLinea(Linea linea);
    public DtoResumen introducirMoneda(Denominacion denominacion, int cantidad);
    public DtoResumen terminarPrestamo();
    public DtoResumen consultarPrestamo(int numeroPrestamo);
}
