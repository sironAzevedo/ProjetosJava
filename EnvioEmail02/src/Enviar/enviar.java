/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enviar;

/**
 *
 * @author siron
 */
public class enviar {
    public static void main(String[] args) {
        email e = new email();
        e.sendEmail("sirondba@gmail.com", "siron.silva@spassu.com.br", "teste email", "primeiro teste email");
    }
}
