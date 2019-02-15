/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iggy
 */
public class ContactTest {

    Contact contact = new Contact();
    /**
     * Test of setBroj method, of class Contact.
     */
    @Test
    public void testSetBroj() throws Exception {
        System.out.println("Testing setBroj");
        String broj = "012-3456789";
        contact.setBroj(broj);
        
        assertEquals(broj, contact.getBroj());
    }
    
    @Test (expected = TelefonException.class)
    public void testExceptionSetBrojWithoutDash() throws Exception {
        System.out.println("Testing exception setBrojWithoutDash");
        String broj = "0123456789";
        contact.setBroj(broj);
        
        assertEquals(broj, contact.getBroj());
    }
    
    @Test (expected = TelefonException.class)
    public void testExceptionSetBrojWithLetters() throws Exception {
        System.out.println("Testing exception setBrojWithLetters");
        String broj = "asd-3456789";        
        contact.setBroj(broj);
        
        assertEquals(broj, contact.getBroj());
    }
    
    @Test (expected = TelefonException.class)
    public void testExceptionSetBrojInsufficientNumbers() throws Exception {
        System.out.println("Testing exception setBrojInsufficientNumbers");
        String broj = "12-34";
        contact.setBroj(broj);
        
        assertEquals(broj, contact.getBroj());
    }
    
}
