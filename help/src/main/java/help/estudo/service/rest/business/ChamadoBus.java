/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help.estudo.service.rest.business;

import help.estudo.service.rest.classEnum.chamado.Status;
import help.estudo.service.rest.classEnum.chamado.Tipo;
import help.estudo.service.rest.data.Chamado;
import help.estudo.service.rest.infra.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author aluno
 */
public class ChamadoBus {

    public Long inserir(Chamado chamado) {
        chamado.setDataRegistro(new Date());
        chamado.setStatus(Status.NOVO);
        chamado.setTipo(Tipo.SOLICITACAO);
        chamado.setUsuario(UsuarioBus.selecionarAluno());
        chamado.setUsuarioStatus(UsuarioBus.selecionarAluno());

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(chamado);
        t.commit();
        return chamado.getId();
    }

    public void alterar(Chamado chamado) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(chamado);
        t.commit();
    }

    public void excluir(long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Chamado c = selecionar(id);

        Transaction t = s.beginTransaction();
        s.delete(c);
        t.commit();
    }

    public Chamado selecionar(long id) {
        return (Chamado) HibernateUtil.getSessionFactory().openSession().get(Chamado.class, id);
    }

    public List<Chamado> listar() {
        return (List<Chamado>) HibernateUtil.getSessionFactory().openSession().createQuery("from Chamado").list();
    }
}
