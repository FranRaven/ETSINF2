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
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;
import static model.Player.checkNickName;

/**
 * FXML Controller class
 *
 * @author Francisco Ramos Guardiola, Antonio Magdalena Gonzalez
 */
public class InicioSesion4Controller implements Initializable {

    @FXML
    private Button cancelfxID;
    @FXML
    private Button jugarfxID;
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
    
    private Connect4 BaseDatos;
    
    public static String p4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try{
            BaseDatos = Connect4.getSingletonConnect4();
            //BaseDatos.registerPlayer("PlayerN1", "player@upv.es", "plaYer123", LocalDate.now(), 650);
            //BaseDatos.registerPlayer("PlayerN2", "player2@upv.es", "plaYer234", LocalDate.now(), 650);
            
        }catch (Connect4DAOException ex){
            Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }    

    @FXML
    private void bCancel(ActionEvent event) {
        Stage myStage = (Stage) this.cancelfxID.getScene().getWindow();
            myStage.close();
    }

    @FXML
    private void bJugar(ActionEvent event) {
        // Cambia a la ventana para jugar contra la máquina
        if(noPuedefxID.getText().equals("Sesion Iniciada")){
            try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/JugarPlayerVista.fxml"));
            Parent root = loader.load();
            
            JugarPlayerController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("VS Computer");
            
            Stage myStage = (Stage) this.jugarfxID.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InicioSesion4Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText(null);
                   alert.setTitle("Cambio Sesion");
                   alert.setContentText("Se ha cambiado de Sesion");
                   alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText(null);
                   alert.setTitle("Codigo Verificacion");
                   alert.setContentText("Inicia Sesion antes de Jugar");
                   alert.showAndWait();
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
        
        String x = JugarPlayerController.b;
        String y = usuariofxID.getText();
        if(!y.equals(x)){
        if(checkNickName(usuariofxID.getText())){
            Player p = BaseDatos.loginPlayer((usuariofxID.getText()), contraseñafxID.getText());
            if(p != null){
                p4 =usuariofxID.getText();
                if(p.checkCredentials(usuariofxID.getText(), contraseñafxID.getText())){
                noPuedefxID.setText("Sesion Iniciada");
            }
            }
            
        }
        }else{
            
        noPuedefxID.setText("Usuario Ya Iniciado");
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
            java.util.logging.Logger.getLogger(InicioSesion4Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
