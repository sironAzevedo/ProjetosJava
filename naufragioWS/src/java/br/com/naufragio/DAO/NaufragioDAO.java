package br.com.naufragio.DAO;

import br.com.valueobjects.NaufragioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NaufragioDAO {
    
    public boolean inserirUsuario(NaufragioVO naufragio){
        
        try {
            Connection conn = ConnectionDB.obterConexao();
            
            String inserir = "INSERT INTO naufragio VALUES(?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(inserir);
            
            pstm.setString(1, naufragio.getNome());
            pstm.setString(2, naufragio.getProfundidade());
            pstm.setString(3, naufragio.getLocalização());
            pstm.setString(4, naufragio.getHistoria());
            pstm.setString(5, naufragio.getFoto());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true; 
    }  
    
    public boolean atualizarUsuario(NaufragioVO naufragio){
        
        try {
            Connection conn = ConnectionDB.obterConexao();
            
            String inserir = "UPDATE naufragio SET(nome = ?, profundidade = ?, localizacao = ?, historia = ?,foto = ?, WHERE id = ?)";
            PreparedStatement pstm = conn.prepareStatement(inserir);
            
            pstm.setString(1, naufragio.getNome());
            pstm.setString(2, naufragio.getProfundidade());
            pstm.setString(3, naufragio.getLocalização());
            pstm.setString(4, naufragio.getHistoria());
            pstm.setString(5, naufragio.getFoto());
            pstm.setLong(6, naufragio.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true; 
    }  
    
    public boolean excluirUsuario(NaufragioVO naufragio){ 
        try {
            Connection conn = ConnectionDB.obterConexao();
            String excluir = "DELETE FROM naufragio WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(excluir);
            pstm.setLong(1, naufragio.getId());
            
            pstm.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
        
        return true; 
    }
    
    public ArrayList<NaufragioVO> buscaTodosNaufragio(){
        ArrayList<NaufragioVO> lista = new ArrayList<NaufragioVO>();
        
         NaufragioVO naufragio = null;
        
        try {
            Connection conn = ConnectionDB.obterConexao();
            String listar = "SELECT * FROM naufragio";
            PreparedStatement pstm = conn.prepareStatement(listar); 
            
            ResultSet rSet = pstm.executeQuery();
            
            while(rSet.next()){
                naufragio = new NaufragioVO();
                naufragio.setId(rSet.getLong(1));
                naufragio.setNome(rSet.getString(2));
                naufragio.setProfundidade(rSet.getString(3));
                naufragio.setLocalização(rSet.getString(4));
                naufragio.setHistoria(rSet.getString(5));
                naufragio.setFoto(rSet.getString(6));
            }
            
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
        return lista;
    }
    
    public NaufragioVO buscaNaufragioPorId(Long id){
        NaufragioVO naufragio = null;
        
        try {
            Connection conn = ConnectionDB.obterConexao();
            String listar = "SELECT * FROM naufragio WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(listar);
            pstm.setLong(1, id);
            
            ResultSet rSet = pstm.executeQuery();
            
            if(rSet.next()){
                naufragio = new NaufragioVO();
                naufragio.setId(rSet.getLong(1));
                naufragio.setNome(rSet.getString(2));
                naufragio.setProfundidade(rSet.getString(3));
                naufragio.setLocalização(rSet.getString(4));
                naufragio.setHistoria(rSet.getString(5));
                naufragio.setFoto(rSet.getString(6));
            }else{
                return naufragio;
            }
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
        return naufragio;
    }
        
}
