/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.DefaultListModel;
import javax.swing.JTable;

/**
 *
 * @author Adrian Castillo
 */
public interface tablaDAO extends Remote{
    public void cargaAutomatica(String nombre, String apellido, String id,Long salario, String cargo, JTable tabla);
    public void eliminarFilaSeleccionada(JTable tabla);
    public void buscarFilaPorCampo(JTable tabla, String campo, String valor);
}
