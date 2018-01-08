
package br.com.valueobjects;


public class NaufragioVO {
    
    private long id;
    private String nome;
    private String profundidade;
    private String localização;
    private String historia;
    private String foto;

    public NaufragioVO() {
        
    }  

    public NaufragioVO(long id, String nome, String profundidade, String localização, String historia, String foto) {
        this.id = id;
        this.nome = nome;
        this.profundidade = profundidade;
        this.localização = localização;
        this.historia = historia;
        this.foto = foto;
    }  
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(String profundidade) {
        this.profundidade = profundidade;
    }

    public String getLocalização() {
        return localização;
    }

    public void setLocalização(String localização) {
        this.localização = localização;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    @Override
    public String toString(){
        return nome + "" + profundidade;
        
    } 
}
