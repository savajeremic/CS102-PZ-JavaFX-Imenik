
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
 *
 * @author Jack
 */
public class AddUserLogin{
    /**
     * addUserWindow je prozor u kojem dodajete novog korisnika
     * 
     */
    public static void addUserWindow()  {
    
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
            if(tfUsername.getText().trim().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Username field empty!");
                alert.setContentText("Try again.");
                alert.showAndWait();
            }
            else if(pfPassword.getText().trim().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Password field empty!");
                alert.setContentText("Try again.");
                alert.showAndWait();
            }
            else
            {
                TableUser.addUser(tfUsername.getText(), pfPassword.getText());
                stageAdd.close();
            }
        });
        
        Button btnBack = new Button("Back");
        btnBack.setStyle("-fx-color: #6495ED");
        btnBack.setOnAction((ActionEvent event) -> {
            stageAdd.close();
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
