/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Connect4;
import controlador.InicioSesionController;
import controlador.InicioSesion3Controller;
import java.awt.Paint;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Player;

/**
 * FXML Controller class
 *
 * @author Francisco Ramos Guardiola, Antonio Magdalena Gonzalez
 */
public class JugarMaquinaController implements Initializable {

    @FXML
    private ImageView avatar1fxID;
    @FXML
    private Label usuario1fxID;
    @FXML
    private Label puntos1fxID;
    @FXML
    private Button cambiofxID;
    @FXML
    private ImageView avatar2fxID;
    @FXML
    private Label usuario2fxID;
    @FXML
    private Label puntos2fxID;
    @FXML
    private Button salirfxID;
    
    private Connect4 BaseDatos;
    @FXML
    private GridPane gridPanefxID;
    
    String [] [] tableroArray = new String [8][7];
    
    @FXML
    private Circle azulfxID;
    @FXML
    private Region reg_1;
    @FXML
    private Region reg_2;
    @FXML
    private Region reg_3;
    @FXML
    private Region reg_4;
    @FXML
    private Region reg_5;
    @FXML
    private Region reg_6;
    @FXML
    private Region reg_7;
    @FXML
    private Region reg_8;
    @FXML
    private Circle rojofxID;
    
    private int turno = 0;
    
    int c1 = 6;
    int c2 = 6;
    int c3 = 6;
    int c4 = 6;
    int c5 = 6;
    int c6 = 6;
    int c7 = 6;
    int c8 = 6;
    
    String a;
    @FXML
    private Button nuevafxID;
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
        
        if(InicioSesion3Controller.p3 == null){
            a = InicioSesionController.p1;
        }else{
            a = InicioSesion3Controller.p3;
        }
        
        Player p = BaseDatos.getPlayer(a);
        avatar1fxID.setImage(p.getAvatar());
        usuario1fxID.setText(p.getNickName());
        String l = Integer.toString(p.getPoints());
        puntos1fxID.setText(l);
        
        Image imgC = new Image("/images/avatar3.png");
        avatar2fxID.setImage(imgC);
        // TODO
        
    }    

    @FXML
    private void bCambio(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InicioSesion3Vista.fxml"));
            Parent root = loader.load();
            
            InicioSesion3Controller controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); 
            stage.setTitle("Iniciar Sesion");
            
            Stage myStage = (Stage) this.cambiofxID.getScene().getWindow();
            myStage.close();
            
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(JugarMaquinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void bSalir(ActionEvent event) {
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
            
            Stage myStage = (Stage) this.salirfxID.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(JugarMaquinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        InicioSesion3Controller.p3 = null;
    }

    
    
    @FXML
    private void cCol1(MouseEvent event) throws Connect4DAOException {
        Circle circuloA = new Circle(azulfxID.getRadius(), azulfxID.getFill());
        Circle circuloR = new Circle(rojofxID.getRadius(), rojofxID.getFill());
        gridPanefxID.setHalignment(circuloA, HPos.CENTER);
        gridPanefxID.setHalignment(circuloR, HPos.CENTER);
        
        gridPanefxID.setValignment(circuloA, VPos.CENTER);
        gridPanefxID.setValignment(circuloR, VPos.CENTER);
        
        
        
        
        if(turno == 0){
            if(c1 >= 0){
                gridPanefxID.add(circuloA, 0, c1);
                circuloA.autosize();
                tableroArray [0][c1] = "0";
                ganador(c1, 0, "0");
                c1--;
                if(c1<0){
                    reg_1.setDisable(true);
                }
                turno = 1;
            }
        }else{
            if(c1 >= 0){
                gridPanefxID.add(circuloR, 0, c1);
                circuloR.autosize();
                tableroArray[0][c1] = "1";
                ganador(c1, 0, "1");
                c1--;
                if(c1<0){
                    reg_1.setDisable(true);
                }
                turno = 0;
            }
            
        }
    }

    @FXML
    private void cCol2(MouseEvent event) throws Connect4DAOException {
        Circle circuloA = new Circle(azulfxID.getRadius(), azulfxID.getFill());
        Circle circuloR = new Circle(rojofxID.getRadius(), rojofxID.getFill());
        gridPanefxID.setHalignment(circuloA, HPos.CENTER);
        gridPanefxID.setHalignment(circuloR, HPos.CENTER);
        
        gridPanefxID.setValignment(circuloA, VPos.CENTER);
        gridPanefxID.setValignment(circuloR, VPos.CENTER);
        
        if(turno == 0){
            if(c2 >= 0){
                gridPanefxID.add(circuloA, 1, c2);
                circuloA.autosize();
                tableroArray [1][c2] = "0";
                ganador(c2, 1, "0");
                c2--;
                if(c2<0){
                    reg_2.setDisable(true);
                }
                turno = 1;
            }
        }else{
            if(c2 >= 0){
                gridPanefxID.add(circuloR, 1, c2);
                circuloR.autosize();
                tableroArray[1][c2] = "1";
                ganador(c2, 1, "1");
                c2--;
                if(c2<0){
                    reg_2.setDisable(true);
                }
                turno = 0;
            }
            
        }
    }

    @FXML
    private void cCol3(MouseEvent event) throws Connect4DAOException {
        Circle circuloA = new Circle(azulfxID.getRadius(), azulfxID.getFill());
        Circle circuloR = new Circle(rojofxID.getRadius(), rojofxID.getFill());
        gridPanefxID.setHalignment(circuloA, HPos.CENTER);
        gridPanefxID.setHalignment(circuloR, HPos.CENTER);
        
        gridPanefxID.setValignment(circuloA, VPos.CENTER);
        gridPanefxID.setValignment(circuloR, VPos.CENTER);
        
        if(turno == 0){
            if(c3 >= 0){
                gridPanefxID.add(circuloA, 2, c3);
                circuloA.autosize();
                tableroArray [2][c3] = "0";
                ganador(c3, 2, "0");
                c3--;
                if(c3<0){
                    reg_3.setDisable(true);
                }
                turno = 1;
            }
        }else{
            if(c3 >= 0){
                gridPanefxID.add(circuloR, 2, c3);
                circuloR.autosize();
                tableroArray[2][c3] = "1";
                ganador(c3, 2, "1");
                c3--;
                if(c3<0){
                    reg_3.setDisable(true);
                }
                turno = 0;
            }
            
        }
    }

    @FXML
    private void cCol4(MouseEvent event) throws Connect4DAOException {
        Circle circuloA = new Circle(azulfxID.getRadius(), azulfxID.getFill());
        Circle circuloR = new Circle(rojofxID.getRadius(), rojofxID.getFill());
        gridPanefxID.setHalignment(circuloA, HPos.CENTER);
        gridPanefxID.setHalignment(circuloR, HPos.CENTER);
        
        gridPanefxID.setValignment(circuloA, VPos.CENTER);
        gridPanefxID.setValignment(circuloR, VPos.CENTER);
        
        if(turno == 0){
            if(c4 >= 0){
                gridPanefxID.add(circuloA, 3, c4);
                circuloA.autosize();
                tableroArray [3][c4] = "0";
                ganador(c4, 3, "0");
                c4--;
                if(c4<0){
                    reg_4.setDisable(true);
                }
                turno = 1;
            }
        }else{
            if(c4 >= 0){
                gridPanefxID.add(circuloR, 3, c4);
                circuloR.autosize();
                tableroArray[3][c4] = "1";
                ganador(c4, 3, "1");
                c4--;
                if(c4<0){
                    reg_4.setDisable(true);
                }
                turno = 0;
            }
            
        }
    }

    @FXML
    private void cCol5(MouseEvent event) throws Connect4DAOException {
        Circle circuloA = new Circle(azulfxID.getRadius(), azulfxID.getFill());
        Circle circuloR = new Circle(rojofxID.getRadius(), rojofxID.getFill());
        gridPanefxID.setHalignment(circuloA, HPos.CENTER);
        gridPanefxID.setHalignment(circuloR, HPos.CENTER);
        
        gridPanefxID.setValignment(circuloA, VPos.CENTER);
        gridPanefxID.setValignment(circuloR, VPos.CENTER);
        
        if(turno == 0){
            if(c5 >= 0){
                gridPanefxID.add(circuloA, 4, c5);
                circuloA.autosize();
                tableroArray [4][c5] = "0";
                ganador(c5, 4, "0");
                c5--;
                if(c5<0){
                    reg_5.setDisable(true);
                }
                turno = 1;
            }
        }else{
            if(c5 >= 0){
                gridPanefxID.add(circuloR, 4, c5);
                circuloR.autosize();
                tableroArray[4][c5] = "1";
                ganador(c5, 4, "1");
                c5--;
            if(c5<0){
                    reg_5.setDisable(true);
                }
                turno = 0;
            }
            
        }
    }
    

    @FXML
    private void cCol6(MouseEvent event) throws Connect4DAOException {
        Circle circuloA = new Circle(azulfxID.getRadius(), azulfxID.getFill());
        Circle circuloR = new Circle(rojofxID.getRadius(), rojofxID.getFill());
        gridPanefxID.setHalignment(circuloA, HPos.CENTER);
        gridPanefxID.setHalignment(circuloR, HPos.CENTER);
        
        gridPanefxID.setValignment(circuloA, VPos.CENTER);
        gridPanefxID.setValignment(circuloR, VPos.CENTER);
        
        if(turno == 0){
            if(c6 >= 0){
                gridPanefxID.add(circuloA, 5, c6);
                circuloA.autosize();
                tableroArray [5][c6] = "0";
                ganador(c6, 5, "0");
                c6--;
                if(c6<0){
                    reg_6.setDisable(true);
                }
                turno = 1;
            }
        }else{
            if(c6 >= 0){
                gridPanefxID.add(circuloR, 5, c6);
                circuloR.autosize();
                tableroArray[5][c6] = "1";
                ganador(c6, 5, "1");
                c6--;
                if(c6<0){
                    reg_6.setDisable(true);
                }
                turno = 0;
            }
            
        }
    }

    @FXML
    private void cCol7(MouseEvent event) throws Connect4DAOException {
        Player g1 = BaseDatos.getPlayer(a);
        Circle circuloA = new Circle(azulfxID.getRadius(), azulfxID.getFill());
        Circle circuloR = new Circle(rojofxID.getRadius(), rojofxID.getFill());
        gridPanefxID.setHalignment(circuloA, HPos.CENTER);
        gridPanefxID.setHalignment(circuloR, HPos.CENTER);
        
        gridPanefxID.setValignment(circuloA, VPos.CENTER);
        gridPanefxID.setValignment(circuloR, VPos.CENTER);
        
        if(turno == 0){
            if(c7 >= 0){
                gridPanefxID.add(circuloA, 6, c7);
                circuloA.autosize();
                tableroArray [6][c7] = "0";
                ganador(c7, 6, "0"); 
                c7--;
                if(c7<0){
                    reg_7.setDisable(true);
                }
                turno = 1;
                
                
            }
        }else{
            if(c7 >= 0){
                gridPanefxID.add(circuloR, 6, c7);
                circuloR.autosize();
                tableroArray[6][c7] = "1";
                ganador(c7, 6, "1");
                c7--;
                if(c7<0){
                    reg_7.setDisable(true);
                }
                turno = 0;
                
            }
            
        }
    }

    @FXML
    private void cCol8(MouseEvent event) throws Connect4DAOException {
        Player g1 = BaseDatos.getPlayer(a);
        Circle circuloA = new Circle(azulfxID.getRadius(), azulfxID.getFill());
        Circle circuloR = new Circle(rojofxID.getRadius(), rojofxID.getFill());
        gridPanefxID.setHalignment(circuloA, HPos.CENTER);
        gridPanefxID.setHalignment(circuloR, HPos.CENTER);
        
        gridPanefxID.setValignment(circuloA, VPos.CENTER);
        gridPanefxID.setValignment(circuloR, VPos.CENTER);
        
        gridPanefxID.scaleShapeProperty();
        
        if(turno == 0){
            if(c8 >= 0){
                gridPanefxID.add(circuloA, 7, c8);
                circuloA.autosize();
                tableroArray [7][c8] = "0";
                ganador(c8, 7, "0");
                c8--;
                turno = 1;
            }else{
                reg_8.setDisable(true);
            }
            
           }else{
            if(c8 >= 0){
                gridPanefxID.add(circuloR, 7, c8);
                circuloR.autosize();
                tableroArray[7][c8] = "1";
                ganador(c8, 7, "1");
                
                c8--;
                
                turno = 0;
            }else{
                reg_8.setDisable(true);
            }
            
            
            
        }
        
    }
    
    /*public void comprobarGanador(int x, int y, int p) throws Connect4DAOException{
        Player g1 = BaseDatos.getPlayer(a);
            if(ganador(x, y, p)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText(null);
                   alert.setContentText("Gana: " + a);
                   alert.showAndWait();
                   
                g1.plusPoints(5);
            }
    }*/
    
    public void ganador(int fila, int columna, String player) throws Connect4DAOException{
        int contador = 0;
        Player g1 = BaseDatos.getPlayer(a);
        
        //Comprobar Abajo
        for(int i = fila; i < 7; i++){
            if(tableroArray[columna][i] == player){
                contador++;
            }else{
                contador = 0;
            }
            if(contador == 4){
                if(player == "0"){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Gana: " + a);
                    alert.showAndWait();
                   
                g1.plusPoints(5);
                }
                if(player == "1"){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Gana Maquina");
                    alert.showAndWait();
                }
            }
        }
        contador = 0;
        
        //Comprobar Derecha
        for(int i = columna; i < 8; i++){
            if(tableroArray[i][fila] == player){
                contador++;
            }else{
                contador = 0;
            }
            if(contador == 4){
                if(player == "0"){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Gana: " + a);
                    alert.showAndWait();
                   
                g1.plusPoints(5);
                }
                if(player == "1"){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Gana Maquina");
                    alert.showAndWait();
                }
            }
        }
        contador = 0;
        
        //Comprobar Izquierda
        for(int i = columna; i >= 0; i--){
            if(tableroArray[i][fila] == player){
                contador++;
            }else{
                contador = 0;
            }
            if(contador == 4){
                if(player == "0"){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Gana: " + a);
                    alert.showAndWait();
                   
                g1.plusPoints(5);
                }
                if(player == "1"){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Gana Maquina");
                    alert.showAndWait();
                }
            }
        }
        contador = 0;
        
        //Comprobar Abajo-Izquierda
        for(int i = columna, j = fila; i >= 0 && j < 7; i--, j++){
                if(tableroArray[i][j] == player){
                    contador++;
                }else{
                    contador = 0;
                }
                if(contador == 4){
                    if(player == "0"){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Gana: " + a);
                        alert.showAndWait();
                   
                       g1.plusPoints(5);
                    }
                    if(player == "1"){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Gana Maquina");
                        alert.showAndWait();
                    }
                }
            
        }
        contador = 0;
        
        //Comprobar Abajo-Derecha
        for(int i = columna, j = fila; i<8 && j < 7; i++, j++){
                if(tableroArray[i][j] == player){
                    contador++;
                }else{
                    contador = 0;
                }
                if(contador == 4){
                    if(player == "0"){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Gana: " + a);
                        alert.showAndWait();
                   
                       g1.plusPoints(5);
                    }
                    if(player == "1"){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Gana Maquina");
                        alert.showAndWait();
                    }
                }
            
        }
        contador = 0;
        
        
        //Comprobar Arriba-Izquierda
        for(int i = columna, j = fila; i >= 0 && j >= 0; i--, j--){
                if(tableroArray[i][j] == player){
                    contador++;
                }else{
                    contador = 0;
                }
                if(contador == 4){
                    if(player == "0"){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Gana: " + a);
                        alert.showAndWait();
                   
                       g1.plusPoints(5);
                    }
                    if(player == "1"){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Gana Maquina");
                        alert.showAndWait();
                    }
                }
            
        }
        contador = 0;
        
        //Comprobar Arriba-Derecha
        for(int i = columna, j = fila; i<8 && j >= 0; i++, j--){
                if(tableroArray[i][j] == player){
                    contador++;
                }else{
                    contador = 0;
                }
                
                if(contador == 4){
                    if(player == "0"){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Gana: " + a);
                        alert.showAndWait();
                   
                       g1.plusPoints(5);
                    }
                    if(player == "1"){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Gana Maquina");
                        alert.showAndWait();
                    }
                    
                }
            
        }
        contador = 0;
       
        
        
        
        
    }

    @FXML
    private void bNueva(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/JugarMaquinaVista.fxml"));
            Parent root = loader.load();
            
            JugarMaquinaController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show(); 
            stage.setTitle("Iniciar Sesion");
            
            Stage myStage = (Stage) this.nuevafxID.getScene().getWindow();
            myStage.close();
            
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(JugarMaquinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
