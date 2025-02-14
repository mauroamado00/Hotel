/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Conexion {
    
        private static Conexion Instance = null;
    private Connection con;

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel?user=root&password=Suarez10&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            JOptionPane.showMessageDialog(null, "Conexion exitosa", "Exito", 1);
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException:");
            System.err.println(e.getMessage());
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQLException: " + ex.getMessage(), "Error", 0);
        }
    }

    public static Conexion getInstance() {
        if (Conexion.Instance == null) {
            Conexion.Instance = new Conexion();
        }
        return Conexion.Instance;
    }

    public Statement getStatement() {
        Statement st = null;
        // crear un objeto Statement
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return st;
    }

    @Override
    public void finalize() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}
