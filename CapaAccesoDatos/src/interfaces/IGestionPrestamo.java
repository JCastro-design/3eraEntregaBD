
package interfaces;

import entities.*;
import java.util.ArrayList;

public interface IGestionPrestamo {
    Prestamo crearPrestamo();
    Prestamo buscarPrestamo(int id);
    Linea crearLinea(Prestamo p,Libro l,int cantidad);
    ArrayList<Linea> buscarLineas();
}
