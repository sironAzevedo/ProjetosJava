package br.com.petrobras.sistam.desktop;

import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.entity.Agencia;
import br.com.petrobras.sistam.common.entity.Porto;
import br.com.petrobras.sistam.desktop.caixaentrada.ListagemAgenciamentoSource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SistamFrameModel  extends BasePresentationModel<SistamService> implements ListagemAgenciamentoSource {

    @Override
    public List<Object> atualizarListagemAgenciamento(Agencia agencia, Porto porto) {
        
        //Obtém a data de corte, que é a data atual - 30 dias.
        Calendar dataDeCorte = Calendar.getInstance();
        dataDeCorte.setTime(new Date());
        dataDeCorte.add(Calendar.DAY_OF_MONTH, -30);
        
        return (List) getService().buscarAgenciamentosParaCaixaDeEntrada(agencia, porto, dataDeCorte.getTime());
    }


}
