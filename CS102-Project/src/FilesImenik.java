
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Klasa za upisivanje u fajl
 * @author Jack
 */
public class FilesImenik {
    /**
    * write zapisuje kontakte trenutnog korisnika imenika
    * 
    */
    public static void write(String s) {
        BufferedWriter bw = null;
        File file = new File("currentUserPhonebook.txt");
        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(s);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText("Error writing to file");
            alert.showAndWait();
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(ex.getMessage());
                alert.setContentText("Error while closing BufferedWriter.");
                alert.showAndWait();
            }
        }
    }
    public static void destroyFile(){
        File file = new File("currentUserPhonebook.txt");
        file.delete();
    }
    
    //Cita iz datoteke red po red i ispisuje
    public static ObservableList<String> reading() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("phonebook.txt"));
            String line;
            while((line = br.readLine()) != null){
                oList.add(line);
            }
            br.readLine();
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText("Error finding file.");
            alert.showAndWait();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText("Error reading from file.");
            alert.showAndWait();
        }
        return oList;
    }
}
