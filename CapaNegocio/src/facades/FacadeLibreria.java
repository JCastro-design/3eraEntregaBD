
package facades;

import accesoDatos.RepositorioLibro;
import accesoDatos.RepositorioPrestamo;
import businessConsts.ConstantesNegocio;
import entities.Denominacion;
import entities.DtoResumen;
import entities.Libro;
import entities.Linea;
import entities.Prestamo;
import interfaces.IFacadeLibreria;
import java.util.List;
import java.time.LocalDateTime;

public class FacadeLibreria implements IFacadeLibreria{

    //ATRIBUTOS
    //RELACIONES
    private List<Libro> catalogo;
    private List<Prestamo> prestamos;
    private Prestamo prestamoActual;
    private RepositorioLibro gestionLibro;
    private RepositorioPrestamo gestionPrestamo;
    
    @Override
    public boolean crearNuevoPrestamo() {
    /*
        LocalDateTime fecha = LocalDateTime.now();
        int numero;
        
        if(la consulta que hace el count de todas las tuplas de la tabla prestamo retorna 0)
        {
            numero = 100; //El primer numero de prestamo va a ser 100
        }
        else
        {
            numero = (select max(numero) from prestamo)++; //Cuando ya hallan tuplas en la tabla prestamo -> numero va a ser al numero mas grande que haya + 1(nunca se van a repetir)
        }
        
        Prestamo prestamo = new Prestamo(fecha, numero);
        prestamoActual = prestamo; //Se agrega como prestamo actual, ya que es el ultimo que se hizo
    */
    return true; //se retorna agregado
    }

    @Override
    public DtoResumen agregarLinea(Libro libro, int cantidad) {
        
        DtoResumen dto = new DtoResumen();
        
        if(verificarExistencia(libro)) 
        {
            if(verificarDisponibilidad(libro, cantidad))
            {
                boolean yaEsta = false;
                for(Linea line: prestamoActual.getLineas())
                {
                    if(line.getLibroEnPrestamo().getIsbn().equals(libro.getIsbn())) //Si el libro de la nueva linea ya esta en otra linea
                    {
                        yaEsta = true;
                        int cantidadPrevia = prestamoActual.getLineas().get(prestamoActual.getLineas().indexOf(line)).getCantidad(); //Se saca cantidad previa
                        prestamoActual.getLineas().get(prestamoActual.getLineas().indexOf(line)).setCantidad(cantidadPrevia+cantidad); //Se incrementa cantidad de esa linea
                        dto.setTuvoExito(true);
                    }
                }
                
                if(!yaEsta) //Si el libro NO esta en otra linea -> Se crea nueva linea
                {
                    dto.setTuvoExito(crearLinea(libro, cantidad));
                }
            }
            else //No hay unidades suficientes
            {
                dto.setMensaje("No se cuenta con el numero de unidades solicitado!/nEn el inventario hay solo ["+catalogo.get(catalogo.indexOf(libro)).getUnidadesDisponibles()+"] unidades");
                dto.setTuvoExito(false);
            }
        }
        else //No esta en catalogo
        {
            dto.setMensaje("El libro ["+libro.getNombre()+"] NO esta en el catalogo!");
            dto.setTuvoExito(false);
        }
        dto.setPrestamo(prestamoActual);
        dto.setTotal(calcularTotalPrestamo());
        return dto;
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
    
    //---------------------------------------------------[METODOS PRIVADOS]---------------------------------------------------//
    
    private boolean verificarExistencia(Libro libro)
    {
        return catalogo.contains(libro); 
    }
    
    private boolean verificarDisponibilidad(Libro libro, int cantidad)
    {
        return catalogo.get(catalogo.indexOf(libro)).getUnidadesDisponibles() >= cantidad;
    }
    
    private boolean crearLinea(Libro libro, int cantidad)
    {
        Linea linea = new Linea(cantidad, prestamoActual, libro);
        return prestamoActual.getLineas().add(linea);
    }
    
    private double calcularValorLibro(Libro libro)
    {
        return libro.getPrecioBase()+(libro.getNumeroImagenes()*ConstantesNegocio.VALOR_IMAGEN)+(libro.getNumeroVideos()*ConstantesNegocio.VALOR_VIDEO);
    }
    
    private double calcularSubtotalLinea(Linea linea)
    {
        return linea.getCantidad()*calcularValorLibro(linea.getLibroEnPrestamo());
    }
    
    private double calcularTotalPrestamo()
    {
       double total = 0; 
       for(Linea line: prestamoActual.getLineas())
       {
           total += calcularSubtotalLinea(line);
       }
       return total;
    }
}
