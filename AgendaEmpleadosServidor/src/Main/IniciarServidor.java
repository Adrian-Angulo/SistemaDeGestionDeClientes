/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import Controladores.EmpleadosControllers;
import Controladores.UsuariosControllers;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author Adrian Castillo
 */
public class IniciarServidor {
    public static void main(String[] args) {
        try{
            Registry registro = LocateRegistry.createRegistry(7777);
            registro.rebind("RemotoRMI", EmpleadosControllers.getEmpleadosControllers() );
            //registro.rebind("RemotoRMI", UsuariosControllers.getUsuariosControllers());
            System.out.println("Servidor esta activo");
            
        }catch(RemoteException ex){
            System.out.println(ex.getMessage());
            System.out.println("malo");
        }
        
    }
}
