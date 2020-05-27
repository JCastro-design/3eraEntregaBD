
package interfaces;
import java.util.List;
import entities.Libro;

public interface IGestionLibro {
    List<Libro> cargarLibros() throws Exception;
    Libro buscarLibro(String isbn);
}
