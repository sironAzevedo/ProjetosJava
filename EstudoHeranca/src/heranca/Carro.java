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
public abstract class Carro extends Fabricante{
    public String marca;
    public String placa;
    public String modelo;
    public String chasse; 
    
    public String detran(){
        return "vistoria";
    }
    
    public abstract boolean mecanico(String cor);
    
}
