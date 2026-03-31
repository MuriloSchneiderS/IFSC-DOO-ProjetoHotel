package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Check;
import java.util.List;
import model.Check;
import model.CheckQuarto;

public class CheckDAO implements InterfaceDAO<Check>{

    @Override
    public void Create(Check objeto) {
        String sqlInstrucao = "INSERT INTO check("
                + " data_hora_cadastro,"
                + " data_hora_entrada,"
                + " data_hora_saida,"
                + " obs,"
                + " status,"
                + " check_quarto_id)"
                + " VALUES (?,?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataHoraCadastro())));
            pstm.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataHoraEntrada())));
            pstm.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataHoraSaida())));
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setInt(6, objeto.getCheckQuarto().getId());
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            System.getLogger(CheckDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Check Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " data_hora_cadastro,"
                + " data_hora_entrada,"
                + " data_hora_saida,"
                + " obs,"
                + " status,"
                + " check_quarto_id"
                + " FROM check WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Check check = new Check();
        CheckQuarto checkQuarto = new CheckQuarto();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                check.setId(rst.getInt("id"));
                check.setDataHoraCadastro(String.valueOf(rst.getDate("data_hora_cadastro")));
                check.setDataHoraEntrada(String.valueOf(rst.getDate("data_hora_entrada")));
                check.setDataHoraSaida(String.valueOf(rst.getDate("data_hora_saida")));
                check.setObs(rst.getString("obs"));
                check.setStatus(rst.getString("status").charAt(0));
                checkQuarto.setId(rst.getInt("check_quarto_id"));
                check.setCheckQuarto(checkQuarto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return check;
        }
    }

    @Override
    public List<Check> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " data_hora_cadastro,"
                + " data_hora_entrada,"
                + " data_hora_saida,"
                + " obs,"
                + " status,"
                + " check_quarto_id"
                + " FROM check"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Check> listaChecks = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                Check check = new Check();
                CheckQuarto checkQuarto = new CheckQuarto();
                check.setId(rst.getInt("id"));
                check.setDataHoraCadastro(String.valueOf(rst.getDate("data_hora_cadastro")));
                check.setDataHoraEntrada(String.valueOf(rst.getDate("data_hora_entrada")));
                check.setDataHoraSaida(String.valueOf(rst.getDate("data_hora_saida")));
                check.setObs(rst.getString("obs"));
                check.setStatus(rst.getString("status").charAt(0));
                checkQuarto.setId(rst.getInt("check_quarto_id"));
                check.setCheckQuarto(checkQuarto);
                listaChecks.add(check);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaChecks;
        }
    }

    @Override
    public void Update(Check objeto) {
        String sqlInstrucao = "UPDATE check "
                + " SET"
                + " data_hora_cadastro=?,"
                + " data_hora_entrada=?,"
                + " data_hora_saida=?,"
                + " obs=?,"
                + " status=?,"
                + " check_quarto_id=?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataHoraCadastro())));
            pstm.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataHoraEntrada())));
            pstm.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataHoraSaida())));
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setInt(6, objeto.getCheckQuarto().getId());
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(Check objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
