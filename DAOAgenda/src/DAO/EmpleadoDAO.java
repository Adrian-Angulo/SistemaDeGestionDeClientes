/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
//importar libreria de remote
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.JTable;
/**
 *
 * @author Adrian Castillo
 */
public interface EmpleadoDAO extends Remote {
    
    public String[][] AgregarEmpleados(String nombre, String apellido, String id,String salario, String cargo) throws RemoteException;
    public void eliminarEmpleado(JTable tabla)throws RemoteException;
    public void buscarFilaPorCampo(JTable tabla, String campo, String valor)throws RemoteException;
}
