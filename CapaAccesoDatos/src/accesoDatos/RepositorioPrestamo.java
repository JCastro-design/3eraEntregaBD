
package accesoDatos;

import entities.*;
import interfaces.IGestionPrestamo;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;


public class RepositorioPrestamo implements IGestionPrestamo{

    @Override
    public Prestamo crearPrestamo() {
        try(
                Connection c = DriverManager.getConnection(ConstantesOrion.THINCONN,ConstantesOrion.USERNAME,ConstantesOrion.PASSWORD);
                PreparedStatement ps = c.prepareStatement("insert into Prestamo values (sq_prestamo.nextval,sysdate)");
           ){
            int n = ps.executeUpdate();
                try(
                        PreparedStatement ps2 = c.prepareStatement("select * from Prestamo order by id desc");
                        ResultSet rs = ps2.executeQuery();
                ){
                    Prestamo p = new Prestamo();
                    rs.next();
                    p.setFecha(Instant.ofEpochMilli(rs.getDate("fecha").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
                    p.setNumero(rs.getInt("id"));
                    return p;
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Prestamo buscarPrestamo(int id) {
        try(
                Connection c = DriverManager.getConnection(ConstantesOrion.THINCONN,ConstantesOrion.USERNAME,ConstantesOrion.PASSWORD);
                PreparedStatement ps = c.prepareStatement("select * from Prestamo where id = ?");
                ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Prestamo p = new Prestamo();
            rs.next();
            p.setFecha(Instant.ofEpochMilli(rs.getDate("fecha").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
            p.setNumero(rs.getInt("id"));
            return p;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Linea crearLinea(Prestamo p,Libro l, int cantidad) {
        Linea lin = new Linea();
        lin.setCantidad(cantidad);
        lin.setPrestamo(p);
        p.getLineas().add(lin);
        try(
                Connection c = DriverManager.getConnection(ConstantesOrion.THINCONN,ConstantesOrion.USERNAME,ConstantesOrion.PASSWORD);
                PreparedStatement ps = c.prepareStatement("insert into Linea values (?,?,?)");
                ){
            ps.setString(1,l.getIsbn());
            ps.setInt(2,p.getNumero());
            ps.setInt(3, cantidad);
            int n = ps.executeUpdate();
            return (n != 0)?lin:null;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Linea> buscarLineas(int PrestamoId) {
        ArrayList<Linea> lineas = new ArrayList<>();
        try(
                Connection c = DriverManager.getConnection(ConstantesOrion.THINCONN,ConstantesOrion.USERNAME,ConstantesOrion.PASSWORD);
                PreparedStatement ps = c.prepareStatement("select * from Linea where prestamoId = ?");
                
                ){
            ps.setInt(1, PrestamoId);
            ResultSet rs = ps.executeQuery();
                Linea l;
                RepositorioLibro rl = new RepositorioLibro();
                while(rs.next()){
                    l = new Linea();
                    l.setLibroEnPrestamo(rl.buscarLibro(rs.getString("libro_isbn")));
                    l.setPrestamo(buscarPrestamo(rs.getInt("prestamoId")));
                    l.setCantidad(rs.getInt("cantidad"));
                    lineas.add(l);
                }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return lineas;
    }
    
    @Override
    public Linea eliminarLinea(String isbn, int prestamoId) {
        try(
                Connection c = DriverManager.getConnection(ConstantesOrion.THINCONN,ConstantesOrion.USERNAME,ConstantesOrion.PASSWORD);
                PreparedStatement ps = c.prepareStatement("select * from Linea where libro_isbn = ? and prestamoId = ?");
                ){
                // Consulta de la linea a eliminar
                ps.setString(1, isbn);
                ps.setInt(2, prestamoId);
                ResultSet rs = ps.executeQuery();
                rs.next();
                Linea l = new Linea();
                RepositorioLibro rl = new RepositorioLibro();
                //,rs.getInt("prestamoId"),rs.getString("libro_isbn")
                l.setCantidad(rs.getInt("cantidad"));
                l.setLibroEnPrestamo(rl.buscarLibro(rs.getString("libro_isbn")));
                l.setPrestamo(buscarPrestamo(rs.getInt("prestamoid")));
                // Eliminar linea
                try(
                        PreparedStatement ps2 = c.prepareStatement("delete from Linea where libro_isbn = ? and prestamoId = ?");
                        ){
                    int n = ps.executeUpdate();
                    if (n != 0)
                        return l;
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void cancelarPrestamo() {
        try(
                Connection c = DriverManager.getConnection(ConstantesOrion.THINCONN,ConstantesOrion.USERNAME,ConstantesOrion.PASSWORD);
                PreparedStatement ps = c.prepareStatement("select id from Prestamo order by id desc");
                ResultSet rs = ps.executeQuery();
                ){
            // Ultimo numero del prestamo para poderlo usar en la secuencia
            rs.next();
            int n = rs.getInt("id");
            // Eliminar prestamo y reaher la secuencia
            try(
                    PreparedStatement ps2 = c.prepareStatement("delete from Prestamo where id = ?");
                    ){
                ps2.setInt(1, n);
                ps2.executeUpdate();
                    try(
                            PreparedStatement ps3 = c.prepareStatement("drop sequence sq_prestamo");
                            ){
                        int alter = ps3.executeUpdate();
                        try(
                            PreparedStatement ps4 = c.prepareStatement("");
                            ){
                        //ps3.setInt(1, n);
                                alter = ps4.executeUpdate();
                                System.out.println("Cancelado");
                            }catch(Exception ex){
                                System.out.println(ex.getMessage());
                                ex.printStackTrace();
                            }
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
