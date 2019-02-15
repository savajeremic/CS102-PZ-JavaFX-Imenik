/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Exception za unos pogresne forme za telefon
 * @author Jack
 */
class TelefonException extends Exception {
    public TelefonException(){
    }
    
    public TelefonException(String message) {
        super(message);
    }
}
