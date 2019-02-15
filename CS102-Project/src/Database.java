
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Klasa za rad sa bazom podataka
 * @author Jack
 */
public class Database {
    
    protected static java.sql.Connection CONNECTION = null;
    
    /**
     *
     */
    protected static final String URL = "jdbc:mysql://localhost/imenik";
    
    protected static final String USERNAME = "root";
    
    protected static final String PASSWORD = "";
    
    public Database() {
    }
    
    public static Connection getCONNECTION() {
        return CONNECTION;
    }
    
    public static String getURL() {
        return URL;
    }
    
    public static String getUSERNAME() {
        return USERNAME;
    }
    
    public static String getPASSWORD() {
        return PASSWORD;
    }
    
    public static void setCONNECTION(Connection CONNECTION) {
        Database.CONNECTION = CONNECTION;
    }

    @Override
    public String toString() {
        return "Database{" + '}';
    }
}
