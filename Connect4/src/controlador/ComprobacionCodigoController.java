/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import controlador.RecContraseñaController;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Francisco Ramos Guardiola, Antonio Magdalena Gonzalez
 */
public class ComprobacionCodigoController implements Initializable {

    @FXML
    private Label contraseñafxID;
    @FXML
    private TextField codigofxID;
    @FXML
    private Button aceptfxID;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bCodigo(ActionEvent event) {
        
    }

    @FXML
    private void bAcept(ActionEvent event) {
        if(this.codigofxID.getText().equals(RecContraseñaController.getCodigo())){
            contraseñafxID.setText("Contraseña: " + RecContraseñaController.rec1);
        } else{
            contraseñafxID.setText("Codigo Erroneo");
        }
    }
    
}
