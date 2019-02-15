
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
 *
 * @author Jack
 * Meni za Administratora
 */
class AdminWindow extends UserView{
    
    static TableView tableView = new TableView<>();
    
    public static void openAdminWindow(int id) throws TelefonException {
    
        Stage stage = new Stage();
        
        Button btnAdd = new Button("New User");
        btnAdd.setPrefWidth(155);
        btnAdd.setOnAction((ActionEvent event) -> {
            int chosenContactsId = ((User) tableView.getSelectionModel().getSelectedItem()).getId();
            addUserWindow(chosenContactsId);
            stage.close();
        });
        
        Button btnRemove = new Button("Delete User");
        btnRemove.setPrefWidth(155);
        btnRemove.setOnAction((ActionEvent event) -> {
            try {
                int chosenContactsId = ((User) tableView.getSelectionModel().getSelectedItem()).getId();
                removeUserWindow(chosenContactsId);
                stage.close();
            } catch (NullPointerException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(ex.getMessage());
                alert.setContentText("Niste izabrali korisnika. Pokusajte ponovo.");
                alert.showAndWait();
            }
        });
        
        Button btnOut = new Button("LogOut");
        btnOut.setPrefWidth(155);
        btnOut.setOnAction((ActionEvent event) -> {
            stage.close();
            Login.loginWindow(stage);
        });
        
        HBox root = new HBox(30);
        Insets ins = new Insets(10, 10, 10, 10);
        root.setPadding(ins);
        root.setAlignment(Pos.CENTER);
        
        root.getChildren().addAll(btnAdd, btnRemove, btnOut);
        root.setStyle("-fx-background-color: #61a2b1");
        
        BorderPane pane = new BorderPane();
        
        pane.setTop(root);
        pane.setCenter(viewUsers());
        pane.setStyle("-fx-background-color: #61a2b1");

        Scene scene = new Scene(pane, 396, 400);
        scene.getStylesheets().add("resources/css/stylesheet.css");
        stage.setTitle("User");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * viewUsers pravi tableView - tabelu koja prikazuje sve Username-ove i sifre korisnika
     * 
     */
    public static Node viewUsers() {       
 
        TableColumn<Contact, String> nameColumn = new TableColumn<>("Username");
        TableColumn<Contact, String> surnameColumn = new TableColumn<>("Password");
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));        
        nameColumn.setMinWidth(202);
        
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        surnameColumn.setMinWidth(202);
 
        tableView = new TableView<>(TableUser.returnUsers());
 
        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getColumns().setAll(nameColumn, surnameColumn);
        
        VBox vbox = new VBox();
 
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5, 0, 0, 0));
        vbox.getChildren().add(tableView);
        
        return vbox;
    }
}