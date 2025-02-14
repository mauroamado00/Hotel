/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author ADMIN
 */
public class Reservacion {
    
    String Numero;
    String TipodeHabitacion;
    String FechadeReserva;
    String Huespedes;
    String Precio;
    String Piso;
    String Estado;

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getTipodeHabitacion() {
        return TipodeHabitacion;
    }

    public void setTipodeHabitacion(String TipodeHabitacion) {
        this.TipodeHabitacion = TipodeHabitacion;
    }

    public String getFechadeReserva() {
        return FechadeReserva;
    }

    public void setFechadeReserva(String FechadeReserva) {
        this.FechadeReserva = FechadeReserva;
    }

    public String getHuespedes() {
        return Huespedes;
    }

    public void setHuespedes(String Huespedes) {
        this.Huespedes = Huespedes;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public String getPiso() {
        return Piso;
    }

    public void setPiso(String Piso) {
        this.Piso = Piso;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
            
}
