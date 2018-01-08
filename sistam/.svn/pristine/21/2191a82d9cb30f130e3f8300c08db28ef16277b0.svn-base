package br.com.petrobras.sistam.test.builder;

import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.Arquivo;
import br.com.petrobras.sistam.common.enums.PastaAnexo;
import java.lang.reflect.Field;
import java.util.Date;
import org.unitils.util.ReflectionUtils;

public class AnexoBuilder {

    private Anexo anexo;
    
    private AnexoBuilder(Anexo anexo) {
        this.anexo = anexo;
    }
    
    public static AnexoBuilder novoAnexo() {
        return new AnexoBuilder(new Anexo());
    }
    
    public static AnexoBuilder novoAnexo(Anexo anexo) {
        return new AnexoBuilder(anexo);
    }
    
    public Anexo build() {
        return this.anexo;
    }
    
    public AnexoBuilder comId(Long id){
        Field field = ReflectionUtils.getFieldWithName(Anexo.class, "id", false);
        ReflectionUtils.setFieldValue(anexo, field, id);
        return this;
    }
    
    
    public AnexoBuilder comAgenciamento(Agenciamento agenciamento){
        anexo.setAgenciamento(agenciamento);
        return this;
    }
    
    public AnexoBuilder comPasta(PastaAnexo pasta){
        anexo.setPasta(pasta);
        return this;
    }
    
    public AnexoBuilder comNome(String nome){
        anexo.setNome(nome);
        return this;
    }
    
    public AnexoBuilder comTamanhoEmBytes(Long tamanho){
        anexo.setTamanhoEmBytes(tamanho);
        return this;
    }
    
    public AnexoBuilder comExtensao(String extensao){
        anexo.setExtensao(extensao);
        return this;
    }
    
    public AnexoBuilder comArquivo(Arquivo arquivo){
        anexo.setArquivo(arquivo);
        return this;
    }
    
     public AnexoBuilder comChaveUsuario(String chave){
        anexo.setChaveUsuario(chave);
        return this;
    }
    
     public AnexoBuilder comNomeUsuario(String nome){
        anexo.setNomeUsuario(nome);
        return this;
    }
     
    public AnexoBuilder comDataCriacao(Date data){
        anexo.setDataDeCriacao(data);
        return this;
    }

}
