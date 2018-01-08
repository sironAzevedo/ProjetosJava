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
public class Design extends Carro {

    public String cor;
    public String jante;

    @Override
    public boolean mecanico(String cor) {
        if (cor.equalsIgnoreCase("branco")) {
            return true;
        }
        return false;
    }

}
