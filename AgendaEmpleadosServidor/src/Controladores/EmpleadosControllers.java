/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import DAO.EmpleadoDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Adrian Castillo
 */
public class EmpleadosControllers extends UnicastRemoteObject implements EmpleadoDAO{
    
    
    public static EmpleadosControllers empleadosControllers;
    
    // simulacion de base de datos en matriz
    private String[][] datos;
    //Control de registros de la matriz
    private int control=1;
    private EmpleadosControllers() throws RemoteException{
        //ejecuto clase padre UnicastRemoteObject
        super();
        
        //matriz de 6 columnas y 50 filas
        datos=new String[50][5];
        
        //defino usuario por defecto
        datos[0][0]="Adrian";
        datos[0][1]="Castillo";
        datos[0][2]="1080831081";
        datos[0][3]="1100000";
        datos[0][4]="Administrador";
        
    }
    
    public static EmpleadosControllers getEmpleadosControllers(){
        if(empleadosControllers==null){
            try {
                empleadosControllers=new EmpleadosControllers();
            } catch (RemoteException ex) {
                Logger.getLogger(EmpleadosControllers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return empleadosControllers;
    }

    /**
     *
     * @param nombre
     * @param apellido
     * @param id
     * @param salario
     * @param cargo
     * @param tabla
     */
    @Override
    public String[][] AgregarEmpleados(String nombre, String apellido, String id, String salario, String cargo)throws RemoteException {
        
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Nombre");
//        model.addColumn("Apellido");
//        model.addColumn("ID");
//        model.addColumn("Salario");
//        model.addColumn("Cargo");
        
//        for (String[] datos : datos) {
//            datos[0] = nombre;
//            datos[1] = apellido;
//            datos[2] = id;
//            datos[3] = salario;
//            datos[4] = cargo;
//            model.addRow(datos);
//        }
        
        
        datos[this.control][0]=nombre;
        datos[this.control][1]=apellido;
        datos[this.control][2]=id;
        datos[this.control][3]=salario;
        datos[this.control][4]=cargo;
        
        
        control++;
        
       return datos;
    }

    @Override
    public void eliminarEmpleado(JTable tabla)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void buscarFilaPorCampo(JTable tabla, String campo, String valor)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
