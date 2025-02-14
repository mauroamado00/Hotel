/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Reservacion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class pReservacion extends MySQL {
   
      public pReservacion() {
        super();
    }

    public Reservacion CargarDatos(Reservacion Reservacion) {
        try {
            Reservacion = new Reservacion();
            Reservacion.setNumero(rs.getString("numero"));
            Reservacion.setTipodeHabitacion(rs.getString("tipodehabitaciones"));
            Reservacion.setFechadeReserva(rs.getString("fechadereserva"));
            Reservacion.setHuespedes(rs.getString("huespedes"));
            Reservacion.setPrecio(rs.getString("precio"));
            Reservacion.setPiso(rs.getString("piso"));
            Reservacion.setEstado(rs.getString("estado"));
            
            return Reservacion;
        } catch (SQLException ex) {
            Logger.getLogger(pReservacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Reservacion;
    }

    public void GuardarReservacion(Reservacion Reservacion) {
        try {
            boolean existe = false;
            existe = ExisteReservacion(Reservacion);
            if (existe) {
                strSQL = "UPDATE Reservacion SET numero = '" + Reservacion.getNumero()
                        + "' , tipodehabitaciones = '" + Reservacion.getTipodeHabitacion()
                        + "' , fechadereserva = '" + Reservacion.getFechadeReserva()
                        + "' , huespedes = '" + Reservacion.getHuespedes()
                        + "' , precio = '" + Reservacion.getPrecio()
                        + "' , piso = '" + Reservacion.getPiso()
                        + "' , estado = '" + Reservacion.getEstado()
                        + "' where numero = '" + Reservacion .getNumero()+"'";
                update(strSQL);

            } else {
                strSQL = "INSERT INTO Reservacion (numero,tipodehabitaciones,fechadereserva,huespedes,precio,piso,estado ) VALUES ('" + Reservacion.getNumero()
                        + "' , '" + Reservacion.getTipodeHabitacion()
                         + "' , '" + Reservacion.getFechadeReserva()
                         + "' , '" + Reservacion.getHuespedes()
                         + "' , '" + Reservacion.getPrecio()
                         + "' , '" + Reservacion.getPiso()
                         + "' , '" + Reservacion.getEstado()
                        + "')";
                update(strSQL);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(pReservacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean ExisteReservacion(Reservacion Reservacion) throws SQLException {
        boolean existe = false;
        this.seleccionar("SELECT * FROM Reservacion"
                + " where numero = '" + Reservacion.getNumero()
                + "'" );
        try {
            while (rs.next()) {
                existe = true;
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return existe;
    }

    public boolean BorrarReservacion(Reservacion Reservacion) {
        boolean existe = false;
        try {
            existe = ExisteReservacion(Reservacion);
            if (existe) {
                strSQL = "Delete from Reservacion "
                        + " where numero = '" + Reservacion.getNumero()+"'";
                update(strSQL);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Reservacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Reservacion BuscarReservacionNoBorrada(Reservacion Reservacion) {
        Reservacion R = null;
        this.seleccionar("SELECT * "
                + " FROM Reservacion "
                + " where numero = " + Reservacion.getNumero() + " and Borrado = " + false);
        try {
            while (rs.next()) {
                R = this.CargarDatos(R);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return R;
    }

    public List<Reservacion> Reservacion(String nomAtributo, String valAtributo) {
        List<Reservacion> Reservacion = new ArrayList<Reservacion>();
        Reservacion R = null;
        this.seleccionar("SELECT *"
                + " FROM Reservacion where " + nomAtributo + " = '" + valAtributo + "'" + " and Borrado = " + false);
        try {
            while (rs.next()) {
                R = this.CargarDatos(R);

            }
            rs.close();
            for (Reservacion c : Reservacion) {

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return Reservacion;
    }

    public List<Reservacion> Reservacion (String criterio) {
        List<Reservacion> Reservacion = new ArrayList<Reservacion>();
        Reservacion R = null;
        this.seleccionar("SELECT * "
                + " FROM Reservacion "
                + " Where Borrado = " + false
                + " ORDER BY " + criterio);
        try {
            while (rs.next()) {
                R = this.CargarDatos(R);
                Reservacion.add((Reservacion) R);
            }
            rs.close();
            for (Reservacion c : Reservacion) {

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return Reservacion;
    }

    public List<Reservacion> Reservacion() {
        List<Reservacion> Reservacion = new ArrayList<Reservacion>();
        Reservacion R = null;
        this.seleccionar("SELECT *"
                + " FROM Reservacion");
        try {
            while (rs.next()) {
                R = this.CargarDatos(R);
                Reservacion.add((Reservacion) R);             
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return Reservacion;
   }

   
}