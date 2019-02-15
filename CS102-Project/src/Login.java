
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Klasa Login pokrece JavaFX prozor za Login
 * @author Jack
 */
public class Login{

    /**
     * loginWindow je prvi prozor koji se otvara u pocetku programa za Login
     * @param
     */    
    public static void loginWindow(Stage primaryStage) {

        Label title = new Label("PHONEBOOK");
        title.setId("title");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        TextField tfUser = new TextField();
        tfUser.setPromptText("Username");
        tfUser.setAlignment(Pos.CENTER);
        tfUser.setMaxWidth(250);
        
        PasswordField tfPass = new PasswordField();
        tfPass.setPromptText("Password");
        tfPass.setAlignment(Pos.CENTER);
        tfPass.setMaxWidth(250);

        Button btnLogin = new Button("Login");
        btnLogin.setStyle("-fx-font-size: 14px");
        btnLogin.setAlignment(Pos.CENTER);
        btnLogin.setPrefWidth(125);
        btnLogin.setOnAction((ActionEvent event) -> {
            try {
                int userType = TableUser.checkLogin(tfUser.getText(), tfPass.getText());
                
                if (userType == 1) {
                    AdminWindow.openAdminWindow(TableUser.checkContactUser(tfUser.getText()));
                    primaryStage.close();
                }
                else if (userType == 0){
                    FilesImenik.write("Phonebook User: " + tfUser.getText());
                    FilesImenik.write("===============================");
                    FilesImenik.write("===========CONTACTS============");
                    FilesImenik.write("===============================");
                    
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Contacts saved to a File");
                    alert.setContentText("Contacts of User writted to a file currentUserPhonebook.txt");
                    alert.showAndWait();
                    
                    UserWindow.openUserWindow(TableUser.checkContactUser(tfUser.getText()));
                    primaryStage.close();
                }
            } catch (TelefonException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(ex.getMessage());
                alert.setContentText("Try again.");
            }
        });
        
        Button btnNewUser = new Button("New Phonebook User");
        btnNewUser.setOnAction((ActionEvent event) -> {
            AddUserLogin.addUserWindow();
        });
        
        Button btnNumbers = new Button("Serbia Numbers");
        btnNumbers.setOnAction((ActionEvent event) -> {
            Ucitavanje ucitavanje = new Ucitavanje();
            ucitavanje.run();
            serbiaContactsWindow();
        });
        
        BorderPane pane = new BorderPane();
        Insets ins = new Insets(10, 10, 10, 10);
        pane.setPadding(ins);
        
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(btnNewUser, btnNumbers);
        
        pane.setTop(title);
        pane.setBottom(hbox);
        BorderPane.setAlignment(hbox, Pos.CENTER);
        BorderPane.setAlignment(title, Pos.CENTER);
        pane.setStyle("-fx-background-color: #61a2b1");
        
        root.getChildren().addAll(tfUser, tfPass, btnLogin);
        pane.setCenter(root);

        Scene scene = new Scene(pane, 600, 420);
        scene.getStylesheets().add("resources/css/stylesheet.css");

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private static void serbiaContactsWindow() {
        
        Stage stage = new Stage();
        VBox root = new VBox(10);
        Insets ins = new Insets(10, 10, 10, 10);
        root.setPadding(ins);
        root.setAlignment(Pos.CENTER);

        ListView<String> lvIzvestaj = new ListView<>();
        ObservableList<String> olIzvestaj = FXCollections.observableArrayList();
        olIzvestaj = FilesImenik.reading();
        lvIzvestaj.setItems(olIzvestaj);

        Button btBack = new Button("Back");
        btBack.setOnAction((ActionEvent event) -> {
            stage.close();
        });
        
        root.getChildren().addAll(lvIzvestaj, btBack);

        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add("resources/css/stylesheet.css");
        
        stage.setTitle("Serbia Town Contacts");
        stage.setScene(scene);
        stage.show();
    }
}
