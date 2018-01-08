package testecollection;

/**
 *
 */
public class Cliente {

    private String nome;
    private String endereco;
    private String fone;
    private static int count;

    public Cliente() {
    } 
    
    public Cliente(String nome, String endereco, String fone) {
        super();
        this.nome = nome;
        this.endereco = endereco;
        this.fone = fone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((endereco == null) ? 0 : endereco.hashCode());
        result = prime * result + ((fone == null) ? 0 : fone.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Cliente other = (Cliente) obj;
        if (endereco == null) {
            if (other.endereco != null) {
                return false;
            }
        } else if (!endereco.equals(other.endereco)) {
            return false;
        }
        if (fone == null) {
            if (other.fone != null) {
                return false;
            }
        } else if (!fone.equals(other.fone)) {
            return false;
        }
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente " + ++count + ": " + nome + " | " + "Endereço: " + endereco + " | " + "Fone: " + fone;
    }
}
