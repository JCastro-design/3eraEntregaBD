import accesoDatos.*;
import entities.Libro;
import entities.Linea;
import entities.Prestamo;
//import java.util.ArrayList;

public class TestDatos {
    public static void main(String[] args) {
        RepositorioLibro rl = new RepositorioLibro();
        RepositorioPrestamo rp = new RepositorioPrestamo();
        Libro lib = rl.buscarLibro("AAAAA1111111111");
        Prestamo p = rp.crearPrestamo();
        Linea lin = rp.crearLinea(p, lib, 10);
        if(lin != null){
            System.out.println("Exito");
        }
    }
}
