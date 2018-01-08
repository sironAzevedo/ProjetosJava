package br.com.petrobras.sistam.desktop.agenciamento.panels;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agenciamento;
import br.com.petrobras.sistam.common.entity.Anexo;
import br.com.petrobras.sistam.common.entity.Arquivo;
import br.com.petrobras.sistam.common.enums.PastaAnexo;
import br.com.petrobras.sistam.common.util.ConstantesI18N;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnexosPainelModel extends BasePresentationModel<SistamService> {
    private Agenciamento agenciamento;
    private Anexo anexoSelecionado;
    private List<PastaAnexo> listaDePastas;
    private PastaAnexo pastaSelecionada;
    private PastaAnexo pastaSelecionadaNaArvore;

    public AnexosPainelModel() {
        listaDePastas = new ArrayList<PastaAnexo>(Arrays.asList(PastaAnexo.values()));
        listaDePastas.add(0, null);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter e Setters">
    public Agenciamento getAgenciamento() {
        return agenciamento;
    }
    
    public void setAgenciamento(Agenciamento agenciamento) {
        this.agenciamento = agenciamento;
        firePropertyChange("agenciamento", null, null);
    }
    
    public Anexo getAnexoSelecionado() {
        return anexoSelecionado;
    }
    
    public List<PastaAnexo> getListaDePastas() {
        return listaDePastas;
    }
    
    public void setAnexoSelecionado(Anexo anexoSelecionado) {
        this.anexoSelecionado = anexoSelecionado;
        firePropertyChange("anexoSelecionado", null, null);
    }
    
    public PastaAnexo getPastaSelecionada() {
        return pastaSelecionada;
    }
    
    public void setPastaSelecionada(PastaAnexo pastaSelecionada) {
        this.pastaSelecionada = pastaSelecionada;
        firePropertyChange("pastaSelecionada", null, null);
    }

    public PastaAnexo getPastaSelecionadaNaArvore() {
        return pastaSelecionadaNaArvore;
    }
    
    //</editor-fold>
    
    public void anexarArquivo(File[] arquivos){
        List<Anexo> listaAnexos = new ArrayList<Anexo>();
        
        for (File arq : arquivos){
            Anexo anexo = new Anexo();
            anexo.setPasta(pastaSelecionada);
            anexo.setAgenciamento(agenciamento);
            anexo.setNome(arq.getName());
            anexo.setTamanhoEmBytes(arq.length());
            
            //Obtém a extensão do arquivo
            String ext[] = arq.getName().split("\\.");  
            if (ext.length > 1){
                anexo.setExtensao(ext[ext.length - 1]);
            }
            
            //Obtém o arquivo
            Arquivo arquivo = new Arquivo(obterBytes(arq));
            anexo.setArquivo(arquivo);
            
            listaAnexos.add(anexo);
        }
        
        listaAnexos = getService().salvarAnexos(listaAnexos);
        pastaSelecionadaNaArvore = pastaSelecionada;
        setPastaSelecionada(null);
        agenciamento.adicionarAnexos(listaAnexos);
    }
    
    public Arquivo obterArquivoDoAnexoSelecionado(){
        return getService().buscarArquivoDoAnexo(anexoSelecionado);
    }
    
    public void exlcuir(){
        //Atualia o agenciamento já carregado no anexo para evitar erro de lazy.
        anexoSelecionado.setAgenciamento(agenciamento);
        
        //Excluir e atualiza a lista do agenciamentos
        getService().excluirAnexo(anexoSelecionado);
        agenciamento.removerAnexo(anexoSelecionado);
        pastaSelecionadaNaArvore = anexoSelecionado.getPasta();
    }
    
    private byte[] obterBytes(File file){
        FileInputStream fin = null;
        byte[] bytes = null;
        try {
            fin = new FileInputStream(file);
            bytes = new byte[(int)file.length()];
            fin.read(bytes);
            fin.close();
        } catch (IOException ex) {
            throw new BusinessException(ConstantesI18N.ANEXO_ERRO_AO_LER_ARQUIVO);
        }
        return bytes;

    }

    
}
