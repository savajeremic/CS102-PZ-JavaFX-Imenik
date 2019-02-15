
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
 * Klasa za rad sa kontaktima
 * @author Jack
 */
public class TableContact extends Database{
    /**
    * returnContacts vraca listu kontakta identifikovani ID-jem korisnika
    * 
    */
    public static ObservableList<Contact> returnContacts(int userId) {
        ObservableList<Contact> listaKontakta = FXCollections.observableArrayList();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM kontakti WHERE userId = ?";
            PreparedStatement st = CONNECTION.prepareStatement(query);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String broj = rs.getString("broj");
                listaKontakta.add(new Contact(id, ime, prezime, broj, userId));
                FilesImenik.write(ime + " " + prezime + " " + broj);
                FilesImenik.write("-------------------------------");
            }
            st.close();
            CONNECTION.close();
        } catch (SQLException ex) {
            System.out.println("Exception is: " + ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        }         

        return listaKontakta;
    }
    /**
    * returnContacts vraca kontakt koji je identifikovan njegovim ID-jem
    * 
    */
    public static Contact returnContact(int id){
        Contact contact = new Contact();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM kontakti WHERE id = ?";
            PreparedStatement st = CONNECTION.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
            {
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String broj = rs.getString("broj");
                int userId = rs.getInt("userId");
                contact = new Contact(id, ime, prezime, broj, userId);
            }
            
            st.close();
            CONNECTION.close();
        } catch (SQLException ex) {
            System.out.println("Exception is: " + ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("Try Again.");
            alert.showAndWait();
        }         

        return contact;
    }
    /**
     * addContact upisuje novi kontakt u bazu
     * 
     */
    public static void addContact(String ime, String prezime, String broj, int userId) {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Contact contact = new Contact();
            contact.setIme(ime);
            contact.setPrezime(prezime);
            contact.setBroj(broj);
            contact.setUserId(userId);
            String query = "INSERT INTO kontakti (ime, prezime, broj, userId) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ps.setString(1, contact.getIme());
            ps.setString(2, contact.getPrezime());
            ps.setString(3, contact.getBroj());
            ps.setInt(4, contact.getUserId());
            ps.execute();
            ps.close();
            CONNECTION.close();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("New user added");
            alert.setContentText("Dodat je korisnik " + contact + ".");
            alert.showAndWait();
            UserWindow.openUserWindow(userId);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        } catch (TelefonException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(exc.getMessage());
            alert.setContentText("Try again.");
            alert.showAndWait();
        }
    }
    /**
     * removeContact brise kontakt iz baze
     * 
     */
    public static void removeContact(int id, Stage stage, int userId) throws TelefonException {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "DELETE FROM kontakti WHERE id = ?";
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            CONNECTION.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Contact removed");
            alert.setContentText("Contact is removed.");
            alert.showAndWait();
            stage.close();
            UserWindow.openUserWindow(userId);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        }
    }
    /**
     * editContact menja kontakt iz baze
     * 
     */
    public static void editContact(int id, String ime, String prezime, String broj, Stage stage, int userId) throws TelefonException {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE kontakti SET ime = ?, prezime = ?, broj = ? WHERE id = ?";
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ps.setString(1, ime);
            ps.setString(2, prezime);
            ps.setString(3, broj);
            ps.setInt(4, id);
            ps.execute();
            ps.close();
            CONNECTION.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Contact edited");
            alert.setContentText("Contact edited to" + ime + " " + prezime + " " + broj  + ".");
            alert.showAndWait();
            stage.close();
            UserWindow.openUserWindow(userId);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("Try again.");
            alert.showAndWait();
        }
    }
}
