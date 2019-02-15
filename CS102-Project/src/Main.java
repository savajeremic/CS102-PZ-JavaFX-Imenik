/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Klasa koja pokrece program
 * @author Jack
 */
public class Main extends Application {
    
    /**
     * Main Metoda
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        Login.loginWindow(primaryStage);
        FilesImenik.destroyFile();
    }

    /**
     * @param args the command line arguments
     * Pokretanje JavaFX aplikacije
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
