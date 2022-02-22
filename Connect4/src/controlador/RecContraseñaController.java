/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
public class RecContraseñaController implements Initializable {

    @FXML
    private TextField correofxID;
    @FXML
    private TextField usuariofxID;
    @FXML
    private Label noReconocefxID;
    @FXML
    private Button cancelfxID;
    @FXML
    private Button siguientefxID;
    
    private Connect4 BaseDatos;
    public static String rec1;
    
    // creo un numero aleatorio entro 1000 y 9999
    static Random rnd = new Random();
    static int  i = rnd.nextInt(10000) + 1000;
    private static String codigo = Integer.toString(i);
    
     

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
        // TODO
    }    

    @FXML
    private void correoField(ActionEvent event) {
    }

    @FXML
    private void usuarioField(ActionEvent event) {
    }

    // vuelve a la ventana InicioSesionController
    @FXML
    private void bCancel(ActionEvent event) {
        
        Stage myStage = (Stage) this.cancelfxID.getScene().getWindow();
                       myStage.close();
        
    }

    //envía un código a traves de una alerta y abre la ventana de ComprobacionCodigoController
    @FXML
    private void bSiguiente(ActionEvent event) {
        
       if(checkNickName(usuariofxID.getText())){
           Player p = BaseDatos.getPlayer(usuariofxID.getText());
           if(p != null){
               rec1 = p.getPassword();
               if(!p.checkEmail(correofxID.getText()) || !p.checkNickName(usuariofxID.getText())){
                   noReconocefxID.setText("Datos no encontrados");
               }
               if(p.checkEmail(correofxID.getText()) == true && p.checkNickName(usuariofxID.getText()) == true){
                   
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText(null);
                   alert.setTitle("Codigo Verificacion");
                   alert.setContentText(codigo);
                   alert.showAndWait();
       
                   try {
                       
            
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ComprobacionCodigoVista.fxml"));
                       Parent root = loader.load();
            
                       ComprobacionCodigoController controlador = loader.getController();
            
                       Scene scene = new Scene(root);
                       Stage stage = new Stage();
                       stage.initModality(Modality.APPLICATION_MODAL);
                       stage.setScene(scene);
                       stage.show();
                       stage.setTitle("Comprobacion Código");
                       
                       Stage myStage = (Stage) this.siguientefxID.getScene().getWindow();
                       myStage.close();
            
                   } catch (IOException ex) {
                       java.util.logging.Logger.getLogger(RecContraseñaController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
               }
           }
       }else{
           noReconocefxID.setText("Datos no validos");
       }
       
       
    }
    
    // método getter para comprobar que el codigo introducido en ComprobacionCodigo es correcto.
    public static String getCodigo(){
        return codigo;
    }
     
}
