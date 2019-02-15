
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Klasa za rad sa korisnicima
 * @author Jack
 */
class TableUser extends Database{
    /**
     * checkLogin funkcija proverava da li je korisnik Admin ili obican Korisnik Imenika
     * @param
     */
    public static int checkLogin(String username, String password) throws TelefonException {
        
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
            String query = "SELECT * FROM korisnici WHERE username = ?";
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("admin"));
            ps.close();
            CONNECTION.close();
            
            if (user.getPassword().equals(password)) {
                if (user.isAdmin() == true) {
                    return 1;
                }
                else{
                    return 0;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Wrong password!");
                alert.setContentText("Try again.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            System.out.println("Exception is: "+ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Wrong username!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        }
        
        return -1;
    }
    /**
     * checkContactUser funkcija proverava koji korisnik koristi imenik
     * @param
     */
    public static int checkContactUser(String username) {
        
        User user = new User();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
            String query = "SELECT * FROM korisnici WHERE username = ?";
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));
            ps.close();
            CONNECTION.close();
            
        } catch (SQLException ex) {
            System.out.println("Exception is: "+ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Wrong username!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        }
        
        return user.getId();
    }
    /**
     * addUser upisuje novog korisnika u bazu
     * @param
     */
    public static void addUser(String username, String password){
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAdmin(false);
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO korisnici (username, password, admin) VALUES (?, ?, ?)";
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isAdmin());
            ps.execute();
            ps.close();
            CONNECTION.close();
        }
        catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Username exists!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        }
    }
    /**
     * removeUser brise korisnika iz baze
     * @param
     */
    public static void removeUser(int id, Stage stage, int userId) throws TelefonException {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "DELETE FROM korisnici WHERE id = ?";
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            CONNECTION.close();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Contact removed");
            alert.setContentText("Contact removed.");
            alert.showAndWait();
            stage.close();
            AdminWindow.openAdminWindow(userId);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        }
    }
    /**
     * returnUsers funkcija vraca listu korisnika
     * @param
     */
    public static ObservableList<User> returnUsers() {
        ObservableList<User> listaKorisnika = FXCollections.observableArrayList();

        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM korisnici";
            PreparedStatement st = CONNECTION.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                listaKorisnika.add(new User(id, username, password, false));
            }
            st.close();
            CONNECTION.close();
        } catch (SQLException ex) {
            System.out.println("Exception is: "+ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        }         

        return listaKorisnika;
    }
    /**
     * returnUsers funkcija vraca korisnika identifikovanog ID-jem
     * @param
     */
     public static User returnUser(int id){
        User user = new User();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM korisnici WHERE id = ?";
            PreparedStatement st = CONNECTION.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
            {
                String username = rs.getString("username");
                String password = rs.getString("username");
                boolean admin = rs.getBoolean("admin");
                user = new User(id, username, password, admin);
            }
            
            st.close();
            CONNECTION.close();
        } catch (SQLException ex) {
            System.out.println("Exception is: "+ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        }         

        return user;
    }
}
