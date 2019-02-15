
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Otvara prozor za korisnike
 * @author Jack
 */
public class ImenikWindow {
    /**
     * 
     * addContactWindow otvara nov prozor za dodavanje novog kontakta
     */
    protected static void addContactWindow(int userId) throws TelefonException {
        
        Stage stageAdd = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        Insets ins = new Insets(10, 10, 10, 10);
        pane.setPadding(ins);
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblIme = new Label("Ime:");
        Label lblPrezime = new Label("Prezime:");
        Label lblBroj = new Label("Broj:");
        
        TextField tfIme = new TextField();
        TextField tfPrezime = new TextField();
        TextField tfBroj = new TextField();
        
        Button btnOpen = new Button("Add");
        btnOpen.setOnAction((ActionEvent event) -> {
                TableContact.addContact(tfIme.getText(), tfPrezime.getText(), tfBroj.getText(), userId);
                stageAdd.close();
                try {
                    UserWindow.openUserWindow(userId);
                } catch (TelefonException ex) {
                    ex.getMessage();
                }
        });
        Button btnBack = new Button("Back");
        btnBack.setOnAction((ActionEvent event) -> {
            stageAdd.close();
            try {
                UserWindow.openUserWindow(userId);
            } catch (TelefonException ex) {
                ex.getMessage();
            }
        });

        pane.add(lblIme, 0, 0);
        pane.add(lblPrezime, 0, 1);
        pane.add(lblBroj, 0, 2);
        
        pane.add(tfIme, 1, 0);
        pane.add(tfPrezime, 1, 1);
        pane.add(tfBroj, 1, 2);
        
        pane.add(btnOpen, 1, 3);
        pane.add(btnBack, 0, 3);
        pane.setStyle("-fx-background-color: #61a2b1");

        Scene scene = new Scene(pane, 300, 200);
        scene.getStylesheets().add("resources/css/stylesheet.css");
        stageAdd.setTitle("Add Contact");
        stageAdd.setScene(scene);
        stageAdd.show();
    }
    /**
     * 
     * editContactWindow otvara nov prozor za izmenu kontakta
     */
    protected static void editContactWindow(int id, int userId) {
        Stage stageEdit = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        Insets ins = new Insets(10, 10, 10, 10);
        pane.setPadding(ins);
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblIme = new Label("Ime:");
        Label lblPrezime = new Label("Prezime:");
        Label lblBroj = new Label("Broj:");
        
        Contact contact = TableContact.returnContact(id);
        
        TextField tfIme = new TextField(contact.getIme());
        TextField tfPrezime = new TextField(contact.getPrezime());
        TextField tfBroj = new TextField(contact.getBroj());
        
        
        Button btnOpen = new Button("Edit");
        btnOpen.setStyle("-fx-color: #82CAFF");
        btnOpen.setOnAction((ActionEvent event) -> {
            try {
                TableContact.editContact(id ,tfIme.getText(), tfPrezime.getText(), tfBroj.getText(), stageEdit, userId);
            } catch (TelefonException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(ex.getMessage());
                alert.setContentText("Try again.");
            }
        });
        Button btnBack = new Button("Back");
            btnBack.setStyle("-fx-color: #6495ED");
        btnBack.setOnAction((ActionEvent event) -> {
            stageEdit.close();
            try {
                UserWindow.openUserWindow(userId);
            } catch (TelefonException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(ex.getMessage());
                alert.setContentText("Try again.");
            }
        });
        
        pane.add(lblIme, 0, 0);
        pane.add(lblPrezime, 0, 1);
        pane.add(lblBroj, 0, 2);
        
        pane.add(tfIme, 1, 0);
        pane.add(tfPrezime, 1, 1);
        pane.add(tfBroj, 1, 2);
        
        pane.add(btnOpen, 1, 3);
        pane.add(btnBack, 0, 3);
        pane.setStyle("-fx-background-color: #61a2b1");

        Scene scene = new Scene(pane, 300, 200);
        scene.getStylesheets().add("resources/css/stylesheet.css");
        stageEdit.setTitle("Edit Contact");
        stageEdit.setScene(scene);
        stageEdit.show();
    }
    /**
     * 
     * removeContactWindow otvara nov prozor za brisanje kontakta
     */
    protected static void removeContactWindow(int id, int userId) {
        Stage stageRemove = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        Insets ins = new Insets(10, 10, 10, 10);
        pane.setPadding(ins);
        pane.setHgap(10);
        pane.setVgap(10);

        Contact contact = TableContact.returnContact(id);
        
        Label lblText = new Label("Da li zelite da izbrisete kontakt ");
        
        Label lblContact = new Label(contact.toString());
        lblContact.setPrefWidth(500);
        lblContact.setStyle("-fx-text-fill: #8B0000");
        
        Button btnRemove = new Button("Remove");
        btnRemove.setPrefWidth(150);
        btnRemove.setOnAction((ActionEvent event) -> {
            try {
                TableContact.removeContact(id, stageRemove, userId);
            } catch (TelefonException ex) {
                ex.getMessage();
            }
        });
        Button btnBack = new Button("Ne");
        btnBack.setPrefWidth(150);
        btnBack.setOnAction((ActionEvent event) -> {
            stageRemove.close();
            try {
                UserWindow.openUserWindow(userId);
            } catch (TelefonException ex) {
                ex.getMessage();
            }
        });

        pane.add(lblText, 0, 0);
        pane.add(lblContact, 0, 1);
        pane.add(btnRemove, 0, 2);
        pane.add(btnBack, 1, 2);
        pane.setStyle("-fx-background-color: #61a2b1");

        Scene scene = new Scene(pane, 450, 300);
        scene.getStylesheets().add("resources/css/stylesheet.css");
        stageRemove.setTitle("Remove Contact");
        stageRemove.setScene(scene);
        stageRemove.show();
    }
}
