package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.AlocacaoVaga;
import java.util.List;
import model.Check;
import model.VagaEstacionamento;
import model.Veiculo;

public class AlocacaoVagaDAO implements InterfaceDAO<AlocacaoVaga>{

    @Override
    public void Create(AlocacaoVaga objeto) {
        String sqlInstrucao = "INSERT INTO alocacao_vaga("
                + " obs,"
                + " status,"
                + " veiculo_id,"
                + " vaga_estacionamento_id,"
                + " check_id)"
                + " VALUES (?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getObs());
            pstm.setString(2, String.valueOf(objeto.getStatus()));
            pstm.setString(3, objeto.getVeiculo().getId()+"");
            pstm.setString(4, objeto.getVagaEstacionamento().getId()+"");
            pstm.setString(5, objeto.getCheck().getId()+"");
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public AlocacaoVaga Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " obs,"
                + " status,"
                + " veiculo_id,"
                + " vaga_estacionamento_id,"
                + " check_id"
                + " FROM alocacao_vaga WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        AlocacaoVaga alocacao_vaga = new AlocacaoVaga();
        Veiculo veiculo = new Veiculo();
        VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
        Check check = new Check();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                alocacao_vaga.setId(rst.getInt("id"));
                alocacao_vaga.setObs(rst.getString("obs"));
                alocacao_vaga.setStatus(rst.getString("status").charAt(0));
                veiculo = service.VeiculoService.Carregar(rst.getInt("veiculo_id"));
                alocacao_vaga.setVeiculo(veiculo);
                vagaEstacionamento = service.VagaEstacionamentoService.Carregar(rst.getInt("vaga_estacionamento_id"));
                alocacao_vaga.setVagaEstacionamento(vagaEstacionamento);
                check = service.CheckService.Carregar(rst.getInt("check_id"));
                alocacao_vaga.setCheck(check);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return alocacao_vaga;
        }
    }

    @Override
    public List<AlocacaoVaga> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " obs,"
                + " status,"
                + " veiculo_id,"
                + " vaga_estacionamento_id,"
                + " check_id"
                + " FROM alocacao_vaga"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<AlocacaoVaga> listaAlocacaoVagas = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                AlocacaoVaga alocacao_vaga = new AlocacaoVaga();
                Veiculo veiculo = new Veiculo();
                VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
                Check check = new Check();
                alocacao_vaga.setId(rst.getInt("id"));
                alocacao_vaga.setObs(rst.getString("obs"));
                alocacao_vaga.setStatus(rst.getString("status").charAt(0));
                veiculo = service.VeiculoService.Carregar(rst.getInt("veiculo_id"));
                alocacao_vaga.setVeiculo(veiculo);
                vagaEstacionamento = service.VagaEstacionamentoService.Carregar(rst.getInt("vaga_estacionamento_id"));
                alocacao_vaga.setVagaEstacionamento(vagaEstacionamento);
                check = service.CheckService.Carregar(rst.getInt("check_id"));
                alocacao_vaga.setCheck(check);
                listaAlocacaoVagas.add(alocacao_vaga);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaAlocacaoVagas;
        }
    }

    @Override
    public void Update(AlocacaoVaga objeto) {
        String sqlInstrucao = "UPDATE alocacao_vaga "
                + " SET"
                + " obs=?,"
                + " status=?,"
                + " veiculo_id=?,"
                + " vaga_estacionamento_id=?,"
                + " check_id=?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getObs());
            pstm.setString(2, String.valueOf(objeto.getStatus()));
            pstm.setString(3, objeto.getVeiculo().getId()+"");
            pstm.setString(4, objeto.getVagaEstacionamento().getId()+"");
            pstm.setString(5, objeto.getCheck().getId()+"");
            pstm.setString(6, objeto.getId()+"");
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(AlocacaoVaga objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
