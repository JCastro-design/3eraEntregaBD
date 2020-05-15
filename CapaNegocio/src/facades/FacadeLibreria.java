
package facades;

import entities.Denominacion;
import entities.DtoResumen;
import entities.Libro;
import entities.Linea;
import interfaces.IFacadeLibreria;

public class FacadeLibreria implements IFacadeLibreria{

    @Override
    public boolean crearNuevoPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoResumen agregarLinea(Libro libro, int cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoResumen eliminarLinea(Linea linea) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoResumen introducirMoneda(Denominacion denominacion, int cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoResumen terminarPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoResumen consultarPrestamo(int numeroPrestamo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
