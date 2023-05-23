/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package agendarmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import DAO.EmpleadoDAO;
import DAO.UsuariosDAO;
import Vista.VistaIniciarSeccion;
import Vista.VistaMenuPrincipal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
/**
 *
 * @author Adrian Castillo
 */
public class AgendaRMI {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
            Registry registro=LocateRegistry.getRegistry("127.0.0.1",7777);
            EmpleadoDAO empleado = (EmpleadoDAO) registro.lookup("RemotoRMI");
            //UsuariosDAO usuerios = (UsuariosDAO) registro.lookup("RemotoRMI");
            VistaIniciarSeccion iniciarSeccion = VistaIniciarSeccion.getVista();
            System.out.println("esta correcto");
            iniciarSeccion.setVisible(true);
            
        }catch(NotBoundException | RemoteException e){
            System.out.println(""+e);
            System.out.println("hay una execcion");
        }
    }
    
}
