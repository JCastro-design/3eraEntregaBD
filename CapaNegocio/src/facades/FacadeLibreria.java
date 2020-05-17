
package facades;

import accesoDatos.RepositorioLibro;
import accesoDatos.RepositorioPrestamo;
import businessConsts.ConstantesNegocio;
import entities.Denominacion;
import entities.DtoResumen;
import entities.Libro;
import entities.Linea;
import entities.Moneda;
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
        
        DtoResumen dto = new DtoResumen();
        
        if(verificarLinea(linea))
        {
            boolean encontrada = false;
            for (Linea line : prestamoActual.getLineas()) {
                if(line.getLibroEnPrestamo().getIsbn().equals(linea.getLibroEnPrestamo().getIsbn()))
                {
                    encontrada = true;
                    prestamoActual.getLineas().remove(prestamoActual.getLineas().indexOf(line));
                    dto.setTuvoExito(true);
                }
            }
            
            if(!encontrada)
            {
                dto.setMensaje("La linea que llego por parametro no existe en el listado de lineas de este prestamo");
                dto.setTuvoExito(false);
            }
        }
        else
        {
            dto.setMensaje("La linea que llego por parametro es nula");
            dto.setTuvoExito(false);
        }
        dto.setPrestamo(prestamoActual);
        dto.setTotal(calcularTotalPrestamo());
        return dto;
    }

    @Override
    public DtoResumen introducirMoneda(Denominacion denominacion, int cantidad) {
        
        DtoResumen dto = new DtoResumen();
        
        if(validarDenominacion(denominacion))
        {
            Moneda moneda = new Moneda(denominacion);
            
            for(int i = 0; i < cantidad; i++)
            {
                prestamoActual.getPagoMonedas().add(moneda); 
            }
            dto.setTuvoExito(true);
        }
        else
        {
            dto.setMensaje("La denominacion no es valida, porfavor introduzca solo monedas de 500 o 1000");
            dto.setTuvoExito(false);
        }
        
        dto.setSaldoEnMonedas(contarMonedas());
        dto.setPrestamo(prestamoActual);
        dto.setTotal(calcularTotalPrestamo());
        return dto;
    }

    @Override
    public DtoResumen terminarPrestamo() {
        
        DtoResumen dto = new DtoResumen();
        
        if(verificarSaldo())
        {
            decrementarExistencias();
            dto.setTuvoExito(true);
        }
        else
        {
            dto.setMensaje("El saldo en monedas ingresado es insuficiente!\nLe faltan "+(calcularTotalPrestamo()-contarMonedas())+"$ para completar");
            dto.setTuvoExito(false);
        }
        dto.setSaldoEnMonedas(contarMonedas());
        dto.setPrestamo(prestamoActual);
        dto.setTotal(calcularTotalPrestamo());
        dto.setVueltas(calcularVueltas());
        return dto;
    }

    @Override
    public DtoResumen consultarPrestamo(int numeroPrestamo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //---------------------------------------------------[METODOS PRIVADOS]---------------------------------------------------//
    
    private boolean verificarExistencia(Libro libro)
    {   
        return catalogo.contains(libro); //corregir 
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
    
    private boolean verificarLinea(Linea linea)
    {
        return linea != null;
    }
    
    private boolean validarDenominacion(Denominacion denominacion)
    {
        return (denominacion == Denominacion.QUINIENTOS || denominacion == Denominacion.MIL);
    }
    
    private double contarMonedas()
    {
        double totalMonedas = 0;
        for (Moneda coin : prestamoActual.getPagoMonedas()) {
            if(coin.getDenominacion() == Denominacion.QUINIENTOS)
            {
                totalMonedas += 500;
            }
            else if(coin.getDenominacion() == Denominacion.MIL)
            {
                totalMonedas += 1000;
            }
        }
        return totalMonedas;
    }
    
    private boolean verificarSaldo()
    {
        return contarMonedas() >= calcularTotalPrestamo();
    }
    
    private void decrementarExistencias()
    {
        for (Linea line : prestamoActual.getLineas()) {
            //se decrementa el numero de unidades en el libro asociado a la linea
            line.getLibroEnPrestamo().setUnidadesDisponibles(line.getLibroEnPrestamo().getUnidadesDisponibles()-line.getCantidad());
            //se decrementa el numero de unidades en el libro del catalogo
            catalogo.get(catalogo.indexOf(line.getLibroEnPrestamo())).setUnidadesDisponibles(catalogo.get(catalogo.indexOf(line.getLibroEnPrestamo())).getUnidadesDisponibles()-line.getCantidad()); 
        }
    }
    
    private double calcularVueltas()
    {
        return (contarMonedas()-calcularTotalPrestamo());
    }
}
