
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Otvara prozor za brisanje korisnika za administratora
 * @author Jack
 */
public class UserView {
    
    protected static void removeUserWindow(int id) {
        
        Stage stageRemove = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        Insets ins = new Insets(10, 10, 10, 10);
        pane.setPadding(ins);
        pane.setHgap(10);
        pane.setVgap(10);

        User user = TableUser.returnUser(id);
        
        Label lblText = new Label("Do you want to remove the contact ");
        
        Label lblContact = new Label(user.toString());
        lblContact.setPrefWidth(500);
        lblContact.setStyle("-fx-text-fill: #8B0000");
        
        Button btnRemove = new Button("Yes");
        btnRemove.setPrefWidth(150);
        btnRemove.setOnAction((ActionEvent event) -> {
            try {
                TableUser.removeUser(id, stageRemove, id);
            } catch (TelefonException ex) {
                ex.getMessage();
            }
        });
        Button btnBack = new Button("No");
        btnBack.setPrefWidth(150);
        btnBack.setOnAction((ActionEvent event) -> {
            stageRemove.close();
            try {
                AdminWindow.openAdminWindow(id);
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
    
    public static void addUserWindow(int id)  {
    
        Stage stageAdd = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        Insets ins = new Insets(10, 10, 10, 10);
        pane.setPadding(ins);
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblUsername = new Label("Username:");
        Label lblPassword = new Label("Password:");
        
        TextField tfUsername = new TextField();
        PasswordField pfPassword = new PasswordField();
        
        Button btnOpen = new Button("Add");
        btnOpen.setStyle("-fx-color: #82CAFF");
        btnOpen.setOnAction((ActionEvent event) -> {
            TableUser.addUser(tfUsername.getText(), pfPassword.getText());
            try {
                AdminWindow.openAdminWindow(id);
            } catch (TelefonException ex) {
                ex.getMessage();
            }
        });
        
        Button btnBack = new Button("Back");
            btnBack.setStyle("-fx-color: #6495ED");
        btnBack.setOnAction((ActionEvent event) -> {
            stageAdd.close();
            try {
                AdminWindow.openAdminWindow(id);
            } catch (TelefonException ex) {
                ex.getMessage();
            }
        });

        pane.add(lblUsername, 0, 0);
        pane.add(lblPassword, 0, 1);
        
        pane.add(tfUsername, 1, 0);
        pane.add(pfPassword, 1, 1);
        
        pane.add(btnOpen, 1, 3);
        pane.add(btnBack, 0, 3);
        pane.setStyle("-fx-color: #E0B0FF");

        Scene scene = new Scene(pane, 300, 200);
        stageAdd.setTitle("Add Contact");
        stageAdd.setScene(scene);
        stageAdd.show();
    }
}
