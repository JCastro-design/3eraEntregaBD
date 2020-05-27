
package accesoDatos;

import interfaces.IGestionLibro;
import entities.*;
import java.util.*;
import java.sql.*;

public class RepositorioLibro implements IGestionLibro{

    @Override
    public List<Libro> cargarLibros() throws Exception {
        ArrayList<Libro> libros = new ArrayList<>();
        try(
            Connection c = DriverManager.getConnection(ConstantesOrion.THINCONN,ConstantesOrion.USERNAME,ConstantesOrion.PASSWORD);
            PreparedStatement ps = c.prepareStatement("select * from Libro");
            ResultSet rs = ps.executeQuery();
        ){
            Libro aux;
            while(rs.next()){
                aux = new Libro();
                aux.setIsbn(rs.getString("isbn"));
                aux.setNombre(rs.getString("nombre"));
                aux.setPrecioBase(rs.getFloat("pBase"));
                aux.setUnidadesDisponibles(rs.getInt("uDisponibles"));
                aux.setNumeroImagenes(rs.getInt("numImagenes"));
                aux.setNumeroVideos(rs.getInt("numVideos"));
                libros.add(aux);
            }
        }catch(Exception ex){
            throw ex;
        }
        return libros;
    }

    @Override
    public Libro buscarLibro(String isbn) {
        try(
            Connection c = DriverManager.getConnection(ConstantesOrion.THINCONN,ConstantesOrion.USERNAME,ConstantesOrion.PASSWORD);
            PreparedStatement ps = c.prepareStatement("select * from Libro where isbn = ?");
                ){
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Libro l = new Libro();
            l.setIsbn(isbn);
            l.setNombre(rs.getString("nombre"));
            l.setPrecioBase(rs.getFloat("pBase"));
            l.setUnidadesDisponibles(rs.getInt("uDisponibles"));
            l.setNumeroImagenes(rs.getInt("numImagenes"));
            l.setNumeroVideos(rs.getInt("numVideos"));
            return l;
        }catch(Exception es){
            es.printStackTrace();
        }
        return null;
    }
}
