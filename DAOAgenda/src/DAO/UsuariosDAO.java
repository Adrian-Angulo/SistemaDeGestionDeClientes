/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultListModel;

/**
 *
 * @author Adrian Castillo
 */

/**
 * interface UsuariosDAO
 * DAO para utilizar los metodos en el servidor y la interfaz
 * @author sistemas
 */
public interface UsuariosDAO extends Remote{
    
    public boolean login(
            String usuario, 
            String contrasenia
    ) throws RemoteException;
    
    public void AgregarUsuario(
                String usuario, 
                String contrasenia
    ) throws RemoteException;
    
    public void EditarUsuario(
                String usuario, 
                String contrasenia
    ) throws RemoteException;
    
    public void EliminarUsuario(
                String usuario
    ) throws RemoteException;
    
    public DefaultListModel MostrarUsuario(String usuario)throws RemoteException;
    
    public String MostrarContrasenia(String usuario)throws RemoteException;
    
    public String RecuperarContrasenia(String usuario)throws RemoteException;
    public boolean VerificarUsuarios(String usuario, String contrasenia)throws RemoteException;
    public String encriptar(String usuario)throws RemoteException;
    public String desencriptar(String usuario, String contrasenia)throws RemoteException;
    public SecretKeySpec crearClave(String clave)throws RemoteException; 
}

