/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controlador.InicioSesionController;
import static controlador.InicioSesionController.p1;
import java.util.logging.Logger;
import model.Connect4;
import model.Player;
import static model.Player.checkNickName;

/**
 * FXML Controller class
 *
 * @author Francisco Ramos Guardiola, Antonio Magdalena Gonzalez
 */
public class InicioSesion2Controller implements Initializable {

    @FXML
    private Button cancelfxID;
    @FXML
    private TextField usuariofxID;
    @FXML
    private TextField contraseñafxID;
    @FXML
    private Label noPuedefxID;
    @FXML
    private Button iniSesionfxID;
    @FXML
    private Button recuperarfxID;
    @FXML
    private Button jugarfxID;
    @FXML
    private Label usuAnteriorfxID;
    
    private Connect4 BaseDatos;
    
    public static String p2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            BaseDatos = Connect4.getSingletonConnect4();
            
        }catch (Connect4DAOException ex){
            Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE,null,ex);
        }
        String a = InicioSesionController.p1;
        Player p = BaseDatos.getPlayer(a);
        
        usuAnteriorfxID.setText(p.getNickName());
        
    }    

    @FXML
    private void bCancel(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InicioSesionVista.fxml"));
            Parent root = loader.load();
            
            InicioSesionController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Connect4!");
            
            Stage myStage = (Stage) this.cancelfxID.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InicioSesion2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void fieldUsuario(ActionEvent event) {
    }

    @FXML
    private void fieldContraseña(ActionEvent event) {
    }

    @FXML
    private void bIniSesion(ActionEvent event) {
        // crea Player p para iniciar sesion, pasa el nombre de usuario a p1 para poder usar los datos de p en otras ventanas
        String x = InicioSesionController.p1;
        String y = usuariofxID.getText();
        if(!y.equals(x)){
        if(checkNickName(usuariofxID.getText())){
            Player p = BaseDatos.loginPlayer((usuariofxID.getText()), contraseñafxID.getText());
            if(p != null){
                p2 =usuariofxID.getText();
                if(!p.checkCredentials(usuariofxID.getText(), contraseñafxID.getText())){
                noPuedefxID.setText("Datos Incorrectos");
                }else{
                noPuedefxID.setText("Sesion Iniciada");
            }
            }
            
            
        }else{
            noPuedefxID.setText("Usuario No Valido");
        }
        }else{
        noPuedefxID.setText("Usuario Ya Iniciado");
        }
        
        
    }

    @FXML
    private void bRecuperar(ActionEvent event) {
         try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RecContraseñaVista.fxml"));
            Parent root = loader.load();
            
            RecContraseñaController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); 
            stage.setTitle("Inicio Sesion");
            
            
            
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InicioSesion2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bJugar(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/JugarPlayerVista.fxml"));
            Parent root = loader.load();
            
            JugarPlayerController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show(); 
            stage.setTitle("Juego");
            
            Stage myStage = (Stage) this.jugarfxID.getScene().getWindow();
            myStage.close();
            
            
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InicioSesion2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
