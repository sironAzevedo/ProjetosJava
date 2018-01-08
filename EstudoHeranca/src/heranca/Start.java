/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heranca;
/**
 *
 * @author siron
 */
public class Start {

    public static void main(String[] asgs) {
        Design n = new Design();
        n.cor = "Branco";
        n.marca = "Gol";
        n.modelo = "GTI";
        n.placa = "123456";
        n.cnpj = "1234";
        n.empresa = "volks";

        if (n.mecanico(n.cor)) {
            System.out.println("O carro da empresa " + n.empresa + " da marca " + n.marca + ", de cor " + n.cor + " Por favor fazer " + n.detran());
        }else{
            System.out.println(n.cnpj);
        }
    }
}
