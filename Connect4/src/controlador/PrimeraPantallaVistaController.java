/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Francisco Ramos Guardiola, Antonio Magdalena Gonzalez
 */

public class PrimeraPantallaVistaController implements Initializable {

    @FXML
    private Button InicioSesionfxID;
    @FXML
    private Button RegistrofxID;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegistrofxID.setDisable(true);
        
       
        // TODO
    }    

    @FXML
    private void bInicioSesion(ActionEvent event) {
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
            
            Stage myStage = (Stage) this.InicioSesionfxID.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PrimeraPantallaVistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
    }

    @FXML
    private void bRegistro(ActionEvent event) {
        
    }
    
}
