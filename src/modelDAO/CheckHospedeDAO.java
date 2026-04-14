package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CheckHospede;
import java.util.List;
import model.Check;
import model.Hospede;

public class CheckHospedeDAO implements InterfaceDAO<CheckHospede>{

    @Override
    public void Create(CheckHospede objeto) {
        String sqlInstrucao = "INSERT INTO check_hospede("
                + " tipo_hospede,"
                + " obs,"
                + " status,"
                + " check_id,"
                + " hospede_id)"
                + " VALUES (?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getTipoHospede());
            pstm.setString(2, objeto.getObs());
            pstm.setString(3, String.valueOf(objeto.getStatus()));
            pstm.setString(4, Integer.toString(objeto.getCheck().getId()));
            pstm.setString(5, Integer.toString(objeto.getHospede().getId()));
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public CheckHospede Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " tipo_hospede,"
                + " obs,"
                + " status,"
                + " check_id,"
                + " hospede_id"
                + " FROM check_hospede WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CheckHospede checkHospede = new CheckHospede();
        Check check = new Check();
        Hospede hospede = new Hospede();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                checkHospede.setId(rst.getInt("id"));
                checkHospede.setTipoHospede(rst.getString("tipo_hospede"));
                checkHospede.setObs(rst.getString("obs"));
                checkHospede.setStatus(rst.getString("status").charAt(0));
                check.setId(rst.getInt("check_id"));
                checkHospede.setCheck(check);
                hospede.setId(rst.getInt("hospede_id"));
                checkHospede.setHospede(hospede);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return checkHospede;
        }
    }

    @Override
    public List<CheckHospede> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " tipo_hospede,"
                + " obs,"
                + " status,"
                + " check_id,"
                + " hospede_id"
                + " FROM check_hospede"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CheckHospede> listaCheckHospedes = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                CheckHospede checkHospede = new CheckHospede();
                Check check = new Check();
                Hospede hospede = new Hospede();
                checkHospede.setId(rst.getInt("id"));
                checkHospede.setTipoHospede(rst.getString("tipo_hospede"));
                checkHospede.setObs(rst.getString("obs"));
                checkHospede.setStatus(rst.getString("status").charAt(0));
                check.setId(rst.getInt("check_id"));
                checkHospede.setCheck(check);
                hospede.setId(rst.getInt("hospede_id"));
                checkHospede.setHospede(hospede);
                listaCheckHospedes.add(checkHospede);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaCheckHospedes;
        }
    }

    @Override
    public void Update(CheckHospede objeto) {
        String sqlInstrucao = "UPDATE check_hospede "
                + " SET"
                + " tipo_hospede =?,"
                + " obs =?,"
                + " status =?,"
                + " check_id=?,"
                + " hospede_id=?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getTipoHospede());
            pstm.setString(2, objeto.getObs());
            pstm.setString(3, String.valueOf(objeto.getStatus()));
            pstm.setString(4, Integer.toString(objeto.getCheck().getId()));
            pstm.setString(5, Integer.toString(objeto.getHospede().getId()));
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
    public void Delete(CheckHospede objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
