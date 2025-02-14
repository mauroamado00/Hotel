/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.InicioSesion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class pIniciodeSesion extends MySQL{
    
    public pIniciodeSesion() {
        super();
    }

    public InicioSesion CargarDatos(InicioSesion InicioSesion) {
        try {
            InicioSesion = new InicioSesion();
            InicioSesion.setNombre(rs.getString("nombre"));
            InicioSesion.setContraseña(rs.getString("contraseña"));

            return InicioSesion;
        } catch (SQLException ex) {
            Logger.getLogger(pIniciodeSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return InicioSesion;
    }

    public void GuardarInicioSesion(InicioSesion InicioSesion) {
        try {
            boolean existe = false;
            existe = ExisteIniciodeSesion(InicioSesion);
            if (existe) {
                strSQL = "UPDATE InicioSesion SET nombre = '" + InicioSesion.getNombre()
                        + "' , contraseña = '" + InicioSesion.getContraseña()
                        + "' where Nombre = " + InicioSesion.getNombre();
                update(strSQL);

            } else {
                strSQL = "INSERT INTO InicioSesion (nombre, contraseña ) VALUES ('" + InicioSesion.getNombre()
                        + "' , '" + InicioSesion.getContraseña()
                        + "')";
                update(strSQL);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(pIniciodeSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean ExisteIniciodeSesion(InicioSesion InicioSesion) throws SQLException {
        boolean existe = false;
        this.seleccionar("SELECT * FROM InicioSesion"
                + " where nombre = '" + InicioSesion.getNombre()
                + "'" + " and Borrado = " + false);
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

    public boolean BorrarIniciodeSesion(InicioSesion InicioSesion) {
        boolean existe = false;
        try {
            existe = ExisteIniciodeSesion(InicioSesion);
            if (existe) {
                strSQL = "Update IniciodeSesion set Borrado=" + true
                        + " where nombre = " + InicioSesion.getNombre();
                update(strSQL);
                strSQL = "Delete from InicioSesion "
                        + " where contraseña = " + InicioSesion.getContraseña();
                update(strSQL);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public InicioSesion BuscarPersonaNoBorrado(InicioSesion InicioSesion) {
        InicioSesion I = null;
        this.seleccionar("SELECT * "
                + " FROM InicioSesion "
                + " where nombre = " + InicioSesion.getNombre() + " and Borrado = " + false);
        try {
            while (rs.next()) {
                I = this.CargarDatos(I);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return I;
    }

    public List<InicioSesion> InicioSesion(String nomAtributo, String valAtributo) {
        List<InicioSesion> InicioSesion = new ArrayList<InicioSesion>();
        InicioSesion I = null;
        this.seleccionar("SELECT *"
                + " FROM InicioSesion where " + nomAtributo + " = '" + valAtributo + "'" + " and Borrado = " + false);
        try {
            while (rs.next()) {
                I = this.CargarDatos(I);

            }
            rs.close();
            for (InicioSesion c : InicioSesion) {

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return InicioSesion;
    }

    public List<InicioSesion> InicioSesion(String criterio) {
        List<InicioSesion> InicioSesion = new ArrayList<InicioSesion>();
        InicioSesion I = null;
        this.seleccionar("SELECT * "
                + " FROM InicioSesion "
                + " Where Borrado = " + false
                + " ORDER BY " + criterio);
        try {
            while (rs.next()) {
                I = this.CargarDatos(I);
                InicioSesion.add((InicioSesion) I);
            }
            rs.close();
            for (InicioSesion c : InicioSesion) {

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return InicioSesion;
    }

    public List<InicioSesion> InicioSesion() {
        List<InicioSesion> InicioSesion = new ArrayList<InicioSesion>();
        InicioSesion I = null;
        this.seleccionar("SELECT *"
                + " FROM InicioSesion" + " Where Borrado = " + false);
        try {
            while (rs.next()) {
                I = this.CargarDatos(I);
                InicioSesion.add((InicioSesion) I);             
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return InicioSesion;
    }

    public void GuardarInicioSesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
