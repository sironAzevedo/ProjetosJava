package testecollection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TesteCollection {

    public static void main(String[] args) {
        //Trabalhando com a Interface Set no Java

        // <editor-fold defaultstate="collapsed" desc="Testando o Set">
        System.out.println("---------- Testando o Set ----------");
        Set<String> colecaoSet = new HashSet<String>();
        colecaoSet.add("Site");
        colecaoSet.add("Varallos");
        colecaoSet.add("Fóruns Varallos");

        //dados duplicados não são inseridos no Set
        colecaoSet.add("Varallos");
        colecaoSet.add("Site");

        System.out.println("Tamanho coleção Set: " + colecaoSet.size());
        int count = 0;
        for (String valor : colecaoSet) {
            System.out.println(++count + " -> " + valor);
        }
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="Testando o HasSet">
        System.out.println("---------- Testando o HasSet ----------");
        Set<String> cursos = new HashSet<String>();
        cursos.add("PHP");
        cursos.add("Java");
        cursos.add("PL/SQL");
        cursos.add("Adobe");
        cursos.add("PHP");

        Set<String> ord = new TreeSet<String>(cursos);

        System.out.println("Sem ordenação: " + cursos);
        System.out.println("Com ordenação: " + ord);
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="Testando o TreeSet">
        System.out.println("---------- Testando o TreeSet ----------");
        class Ordenacao implements Comparator<Cliente> {

            @Override
            public int compare(Cliente p1, Cliente p2) {
                return p1.getNome().compareTo(p2.getNome());
            }
        }

        TreeSet<Cliente> hsc = new TreeSet<Cliente>(new Ordenacao());
        hsc.add(new Cliente("Pedro de Lara", "Rua 20 de março", "7568-8524"));
        hsc.add(new Cliente("João Delfino", "Rua da Várzea", "3232-1232"));
        hsc.add(new Cliente("Maria Tijuca", "Av. Brasil", "8569-99988"));

        Iterator<Cliente> it = hsc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        // </editor-fold> 
    }
}
