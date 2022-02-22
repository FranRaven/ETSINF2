/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.Connect4;
import model.Player;
import DBAccess.*;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import static model.Player.checkNickName;
import static model.Player.checkPassword;
/**
 * FXML Controller class
 *
 * @author Francisco Ramos Guardiola, Antonio Magdalena Gonzalez
 */
public class InicioSesionController implements Initializable {

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
    private Button playerfxID;
    @FXML
    private Button computerfxID;
    
    private Connect4 BaseDatos;
    
    public static String p1;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            BaseDatos = Connect4.getSingletonConnect4();
            //BaseDatos.registerPlayer("PlayerN1", "player@upv.es", "plaYer123", LocalDate.now(), 650);
            //BaseDatos.registerPlayer("PlayerN2", "player2@upv.es", "plaYer234", LocalDate.now(), 650);
            
        }catch (Connect4DAOException ex){
            Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        // TODO
    }    

    @FXML
    private void bCancel(ActionEvent event) {
        try {
            
            // Vuelve a la primera pestaña en la que elegir si iniciar sesión o registrarse.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PrimeraPantallaVista.fxml"));
            Parent root = loader.load();
            
            PrimeraPantallaVistaController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Connect4!");
            
            Stage myStage = (Stage) this.cancelfxID.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
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
        if(checkNickName(usuariofxID.getText())){
            Player p = BaseDatos.loginPlayer((usuariofxID.getText()), contraseñafxID.getText());
            if(p != null){
                p1 =usuariofxID.getText();
                if(!p.checkCredentials(usuariofxID.getText(), contraseñafxID.getText())){
                noPuedefxID.setText("Datos Incorrectos");
                }else{
                noPuedefxID.setText("Sesion Iniciada");
            }
            }
            
        }
        
        
        
        
    }

    @FXML
    private void bRecuperar(ActionEvent event) {
        
        // Cambia a la ventana de Recuperar contraseña
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RecContraseñaVista.fxml"));
            Parent root = loader.load();
            
            RecContraseñaController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); 
            stage.setTitle("Recuperar Contraseña");
            
            
            
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bPlayer(ActionEvent event) {
        
        //Cambia a la ventana para inciar sesión con el jugador 2
        if(noPuedefxID.getText().equals("Sesion Iniciada")){
            try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InicioSesion2Vista.fxml"));
            Parent root = loader.load();
            
            InicioSesion2Controller controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Inicio Sesion Usuario 2");
            Stage myStage = (Stage) this.playerfxID.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText(null);
                   alert.setTitle("Codigo Verificacion");
                   alert.setContentText("Antes Debes Iniciar Sesion");
                   alert.showAndWait();
        }
        
    }

    @FXML
    private void bComputer(ActionEvent event) {
    // Cambia a la ventana para jugar contra la máquina
        if(noPuedefxID.getText().equals("Sesion Iniciada")){
            try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/JugarMaquinaVista.fxml"));
            Parent root = loader.load();
            
            JugarMaquinaController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("VS Computer");
            
            Stage myStage = (Stage) this.computerfxID.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText(null);
                   alert.setTitle("Codigo Verificacion");
                   alert.setContentText("Inicia Sesion antes de Jugar");
                   alert.showAndWait();
        }
        
        
    }
    
    

}
