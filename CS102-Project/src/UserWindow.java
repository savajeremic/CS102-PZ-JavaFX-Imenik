
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Glavni Prozor za korisnike
 * @author Jack
 */
public class UserWindow extends ImenikWindow{
    
    static TableView tableView = new TableView<>();
    /**
    * openUserWindow otvara prozor za korisnike
    * 
    */
    public static void openUserWindow(int userId) throws TelefonException {
    
        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        HBox root = new HBox(30);
        Insets ins = new Insets(10, 10, 10, 10);
        root.setPadding(ins);
        root.setAlignment(Pos.CENTER);
        
        
        Button btAdd = new Button("New Contact");
        btAdd.setPrefWidth(155);
        btAdd.setOnAction((ActionEvent event) -> {
            try {
                addContactWindow(userId);
                stage.close();
            } catch (TelefonException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(ex.getMessage());
                alert.setContentText("Try again.");
                alert.showAndWait();
            }
        });
        Button btEdit = new Button("Edit Contact");
        btEdit.setPrefWidth(155);
        btEdit.setOnAction((ActionEvent event) -> {
            try {
                int chosenContactsId = ((Contact) tableView.getSelectionModel().getSelectedItem()).getId();
                editContactWindow(chosenContactsId, userId);
                stage.close();
            } catch (NullPointerException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(ex.getMessage());
                alert.setContentText("Please choose a contact. Try again.");
                alert.showAndWait();
            }
        });
        Button btRemove = new Button("Delete Contact");
        btRemove.setPrefWidth(155);
        btRemove.setOnAction((ActionEvent event) -> {
            try {
                int chosenContactsId = ((Contact) tableView.getSelectionModel().getSelectedItem()).getId();
                removeContactWindow(chosenContactsId, userId);
                stage.close();
            } catch (NullPointerException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(ex.getMessage());
                alert.setContentText("Please choose a contact. Try again.");
                alert.showAndWait();
            }
        });
        
        Button btnOut = new Button("LogOut");
        btnOut.setPrefWidth(155);
        btnOut.setOnAction((ActionEvent event) -> {
            stage.close();
            Login.loginWindow(stage);
        });
        
        root.getChildren().addAll(btAdd, btEdit, btRemove, btnOut);
        root.setStyle("-fx-background-color: #61a2b1");
        
        pane.setTop(root);
        pane.setCenter(viewContacts(userId));
        pane.setStyle("-fx-background-color: #61a2b1");

        Scene scene = new Scene(pane, 535, 400);
        scene.getStylesheets().add("resources/css/stylesheet.css");
        stage.setTitle("User");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * viewContacts pravi tableView - tabelu koja prikazuje sve kontakte
     * 
     */
    public static Node viewContacts(int userId) {       
 
        TableColumn<Contact, String> nameColumn = new TableColumn<>("Ime");
        TableColumn<Contact, String> surnameColumn = new TableColumn<>("Prezime");
        TableColumn<Contact, String> phoneColumn = new TableColumn<>("Telefon");
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));        
        nameColumn.setMinWidth(181);
        
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        surnameColumn.setMinWidth(181);
        
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("broj"));
        phoneColumn.setMinWidth(181);
 
        tableView = new TableView<>(TableContact.returnContacts(userId));
        
        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getColumns().setAll(nameColumn, surnameColumn, phoneColumn);
        
        VBox vbox = new VBox();
 
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5, 0, 0, 0));
        vbox.getChildren().add(tableView);
        
        return vbox;
    }
}
