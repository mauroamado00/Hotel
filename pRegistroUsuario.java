/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.RegistroUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class pRegistroUsuario extends MySQL {

    public pRegistroUsuario() {
        super();
    }

    public RegistroUsuario CargarDatos(RegistroUsuario RegistroUsuario) {
        try {
            RegistroUsuario = new RegistroUsuario();
            RegistroUsuario.setNombre(rs.getString("nombre"));
            RegistroUsuario.setContraseña(rs.getString("contraseña"));
            RegistroUsuario.setEmail(rs.getString("email"));

            return RegistroUsuario;
        } catch (SQLException ex) {
            Logger.getLogger(pRegistroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RegistroUsuario;
    }

    public void GuardarRegistroUsuario(RegistroUsuario RegistroUsuario) {
        try {
            boolean existe = false;
            existe = ExisteRegistroUsuario(RegistroUsuario);
            if (existe) {
                strSQL = "UPDATE RegistroUsuario SET nombre = '" + RegistroUsuario.getNombre()
                        + "' , contraseña = '" + RegistroUsuario.getContraseña()
                        + "' where Nombre = " + RegistroUsuario.getNombre();
                update(strSQL);

            } else {
                strSQL = "INSERT INTO RegistroUsuario (nombre, contraseña, email ) VALUES ('" + RegistroUsuario.getNombre()
                        + "' , '" + RegistroUsuario.getContraseña()
                        + "' , '" + RegistroUsuario.getEmail()
                        + "')";
                update(strSQL);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(pRegistroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean ExisteRegistroUsuario(RegistroUsuario RegistroUsuario) throws SQLException {
        boolean existe = false;
        this.seleccionar("SELECT * FROM RegistroUsuario"
                + " where nombre = '" + RegistroUsuario.getNombre()
                + "'");
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

    public boolean BorrarRegistroUsuario(RegistroUsuario RegistroUsuario) {
        boolean existe = false;
        try {
            existe = ExisteRegistroUsuario(RegistroUsuario);
            if (existe) {

                strSQL = "Delete from RegistroUsuario "
                        + " where nombre = '" + RegistroUsuario.getNombre() + "'";
                update(strSQL);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RegistroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public RegistroUsuario BuscarRegistroUsuarioNoBorrada(RegistroUsuario RegistroUsuario) {
        RegistroUsuario R = null;
        this.seleccionar("SELECT * "
                + " FROM RegistroUsuario "
                + " where nombre = '" + RegistroUsuario.getNombre() + "' ");
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

    public boolean ValidarLogin(RegistroUsuario RegistroUsuario) {
        boolean existe = false;

        this.seleccionar("SELECT * "
                + " FROM RegistroUsuario "
                + " where nombre = '" + RegistroUsuario.getNombre() + "' and contraseña ='" + RegistroUsuario.getContraseña() + "'");
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

    public List<RegistroUsuario> RegistroUsuario(String nomAtributo, String valAtributo) {
        List<RegistroUsuario> RegistroUsuario = new ArrayList<RegistroUsuario>();
        RegistroUsuario R = null;
        this.seleccionar("SELECT *"
                + " FROM RegistroUsuario where " + nomAtributo + " = '" + valAtributo + "'" + " and Borrado = " + false);
        try {
            while (rs.next()) {
                R = this.CargarDatos(R);

            }
            rs.close();
            for (RegistroUsuario c : RegistroUsuario) {

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return RegistroUsuario;
    }

    public List<RegistroUsuario> RegistroUsuario(String criterio) {
        List<RegistroUsuario> RegistroUsuario = new ArrayList<RegistroUsuario>();
        RegistroUsuario R = null;
        this.seleccionar("SELECT * "
                + " FROM RegistroUsuario "
                + " Where Borrado = " + false
                + " ORDER BY " + criterio);
        try {
            while (rs.next()) {
                R = this.CargarDatos(R);
                RegistroUsuario.add((RegistroUsuario) R);
            }
            rs.close();
            for (RegistroUsuario c : RegistroUsuario) {

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return RegistroUsuario;
    }

    public List<RegistroUsuario> RegistroUsuario() {
        List<RegistroUsuario> RegistroUsuario = new ArrayList<RegistroUsuario>();
        RegistroUsuario R = null;
        this.seleccionar("SELECT *"
                + " FROM RegistroUsuario" + " Where Borrado = " + false);
        try {
            while (rs.next()) {
                R = this.CargarDatos(R);
                RegistroUsuario.add((RegistroUsuario) R);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return RegistroUsuario;
    }
}
