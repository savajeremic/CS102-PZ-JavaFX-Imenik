
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack
 */
public class Contact {
    
    private int id;
    private String ime;
    private String prezime;
    private String broj;
    private int userId;
    
    public Contact(){
    }

    public Contact(int id, String ime, String prezime, String broj, int userId){
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.broj = broj;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }
    
    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getBroj() {
        return broj;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setBroj(String broj) throws TelefonException{
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        if ((!broj.equals(null) && (!broj.equals(""))))
        {
            Matcher matcher = pattern.matcher(broj);
            if (!matcher.matches()) {
                throw new TelefonException("Broj mora biti u formatu: XXX-XXXXXXX");
            }
            else{
                this.broj = broj;
            }
        }
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " sa brojem: " + broj;
    }
}
