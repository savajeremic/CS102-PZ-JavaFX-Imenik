
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack
 */
//Ucitavanje sadrzaja sa interneta
public class Ucitavanje {
    
    ArrayList<Element> arrayElements;

    public Ucitavanje() {
    }
   
    public void run() {

        try {
            Document doc = (Document) Jsoup.connect("https://en.wikipedia.org/wiki/Telephone_numbers_in_Serbia").get();
            Elements elements = doc.select("table > tbody > tr");
            
            for (Element e : elements) {
               String eHtml = e.text();
                File file = new File("phonebook.txt");
                if(file.length() == 0)
                {
                    writeToFile(eHtml + "\n");
                }
            }                  
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("IOException Error.");
            alert.showAndWait();
        }           
    };
    
    //Upisivanje u fajl
    public void writeToFile(String eHtml){
        
        File file = new File("phonebook.txt");
        FileWriter writer;
        try {
            writer = new FileWriter(file, true);
            PrintWriter output = new PrintWriter(writer);
            output.append(eHtml + "\r\n");              
            output.close();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText("IOException Error.");
            alert.showAndWait();
        }
    }
}
