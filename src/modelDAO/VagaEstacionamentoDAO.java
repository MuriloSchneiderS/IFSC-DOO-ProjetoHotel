package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.VagaEstacionamento;
import java.util.List;

public class VagaEstacionamentoDAO implements InterfaceDAO<VagaEstacionamento>{

    @Override
    public void Create(VagaEstacionamento objeto) {
        String sqlInstrucao = "INSERT INTO vagaEstacionamento("
                + " descricao,"
                + " obs,"
                + " metragem_vaga,"
                + " status)"
                + " VALUES (?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setFloat(3, objeto.getMetragemVaga());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public VagaEstacionamento Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " descricao,"
                + " obs,"
                + " metragem_vaga,"
                + " status"
                + " FROM vagaEstacionamento WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
        
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                vagaEstacionamento.setId(rst.getInt("id"));
                vagaEstacionamento.setDescricao(rst.getString("descricao"));
                vagaEstacionamento.setObs(rst.getString("obs"));
                vagaEstacionamento.setMetragemVaga(rst.getFloat("metragem_vaga"));
                vagaEstacionamento.setStatus(rst.getString("status").charAt(0));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return vagaEstacionamento;
        }
    }

    @Override
    public List<VagaEstacionamento> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " descricao,"
                + " obs,"
                + " metragem_vaga,"
                + " status"
                + " FROM vagaEstacionamento"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<VagaEstacionamento> listaVagasEstacionamento = new ArrayList<>();
        
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (!rst.next()) {
                VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
                
                vagaEstacionamento.setId(rst.getInt("id"));
                vagaEstacionamento.setDescricao(rst.getString("descricao"));
                vagaEstacionamento.setObs(rst.getString("obs"));
                vagaEstacionamento.setMetragemVaga(rst.getFloat("metragem_vaga"));
                vagaEstacionamento.setStatus(rst.getString("status").charAt(0));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaVagasEstacionamento;
        }
    }

    @Override
    public void Update(VagaEstacionamento objeto) {
        String sqlInstrucao = "UPDATE vagaEstacionamento"
                + " SET"
                + " descricao=?,"
                + " obs=?,"
                + " metragem_vaga=?,"
                + " status =?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setFloat(3, objeto.getMetragemVaga());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            pstm.setInt(5, objeto.getId());
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(VagaEstacionamento objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
