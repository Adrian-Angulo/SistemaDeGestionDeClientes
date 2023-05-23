/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import DAO.UsuariosDAO;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultListModel;

/**
 *
 * @author Adrian Castillo
 */
public class UsuariosControllers extends UnicastRemoteObject implements UsuariosDAO{
        
    public static UsuariosControllers usuariosControllers;
    private String claveSecreta= "12345";
    // simulacion de base de datos en matriz
    private String[][] datosUsuarios;
    //Control de registros de la matriz
    private int control=1;
    private UsuariosControllers() throws RemoteException{
        //ejecuto clase padre UnicastRemoteObject
        super();
        
        //matriz de 6 columnas y 50 filas
        datosUsuarios=new String[50][2];
        
        //defino usuario por defecto
        datosUsuarios[0][0]="Adrian";
        datosUsuarios[0][1]="1234";
        
        
    }
    
    public static UsuariosControllers getUsuariosControllers(){
        if(usuariosControllers==null){
            try {
                usuariosControllers=new UsuariosControllers();
            } catch (RemoteException ex) {
                Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return usuariosControllers;
    }

    @Override
    public boolean login(String usuario, String contrasenia) throws RemoteException {
        boolean estado = false;
        
        for(int i=0;i<this.control;i++){
            if((this.datosUsuarios[i][0].equals(usuario)) && (this.datosUsuarios[i][1].equals(contrasenia))){
                estado=true;
                break;
            }
        }
        
        return estado;
    }
    

    @Override
    public void AgregarUsuario(String usuario, String contrasenia) throws RemoteException {
        datosUsuarios[this.control][0]=usuario;
        datosUsuarios[this.control][1]=contrasenia;
        
        
        control++;
    }

    @Override
    public void EditarUsuario(String usuario, String contrasenia) throws RemoteException {
        for(int i=0;i<this.control;i++){
            if(this.datosUsuarios[i][0].equals(usuario)){
                datosUsuarios[i][0]=usuario;
                datosUsuarios[i][1]=contrasenia;
                
                break;
            }
        }
        
    }

    @Override
    public void EliminarUsuario(String usuario) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DefaultListModel MostrarUsuario(String usuario) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String MostrarContrasenia(String usuario) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String RecuperarContrasenia(String usuario) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean VerificarUsuarios(String usuario, String contrasenia) throws RemoteException {
        
        if (usuario.equals(desencriptar(encriptar("Adrian1080"), contrasenia))) {
            //JOptionPane.showMessageDialog(, "Has iniciado sesión correctamente.", "Mensaje de inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Has iniciado seccion");
            return true;
        } else {
            // Login failed
            System.out.println("Contraseña y/o usuarios incorrectos");
            return  false;
        }
        
       
    }

    @Override
    public String encriptar(String usuario) throws RemoteException {
        try {
            SecretKeySpec secretKey = this.crearClave(claveSecreta);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            byte[] datosEncriptar = usuario.getBytes("UTF-8");
            byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
            String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);
            
            return encriptado;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public String desencriptar(String usuario, String contrasenia) throws RemoteException {
        try {
            SecretKeySpec secretKey = this.crearClave(contrasenia);
            
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            byte[] datosDesencriptar = Base64.getDecoder().decode(usuario);
            byte[] bytesDesencriptados = cipher.doFinal(datosDesencriptar);
            String encriptado = new String(bytesDesencriptados);
            
            return encriptado;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public SecretKeySpec crearClave(String clave) throws RemoteException {
        try {
            byte[] claveEncriptar = clave.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            
            claveEncriptar = sha.digest(claveEncriptar);
            claveEncriptar = Arrays.copyOf(claveEncriptar,16);
            
            SecretKeySpec secretKey = new SecretKeySpec(claveEncriptar, "AES");
            
            return secretKey;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
 
