/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 *
 * @author Francisco Ramos Guardiola, Antonio Magdalena Gonzalez
 */
public class Main extends Application{
    
    @Override
    public void start (Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/PrimeraPantallaVista.fxml"));
            Pane ventana = (Pane) loader.load();
            
            //Show the scene containing the root layout.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setTitle("Conecta4!");
            
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}

