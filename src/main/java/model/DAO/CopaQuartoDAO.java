package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CopaQuarto;
import java.util.List;
import model.CheckQuarto;

public class CopaQuartoDAO implements InterfaceDAO<CopaQuarto>{

    @Override
    public void Create(CopaQuarto objeto) {
        String sqlInstrucao = "INSERT INTO copa_quarto("
                + " quantidade,"
                + " data_hora_pedido,"
                + " obs,"
                + " status,"
                + " check_quarto_id)"
                + " VALUES (?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setFloat(1, objeto.getQuantidade());
            pstm.setDate(2, objeto.getDataHoraPedido());
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            pstm.setInt(5, objeto.getCheckQuarto().getId());
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public CopaQuarto Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " quantidade,"
                + " data_hora_pedido,"
                + " obs,"
                + " status,"
                + " check_quarto_id"
                + " FROM copa_quarto WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CopaQuarto copaQuarto = new CopaQuarto();
        CheckQuarto checkQuarto = new CheckQuarto();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                copaQuarto.setId(rst.getInt("id"));
                copaQuarto.setQuantidade(rst.getFloat("quantidade"));
                copaQuarto.setDataHoraPedido(rst.getDate("data_hora_pedido"));
                copaQuarto.setObs(rst.getString("obs"));
                copaQuarto.setStatus(rst.getString("status").charAt(0));
                checkQuarto.setId(rst.getInt("check_quarto_id"));
                copaQuarto.setCheckQuarto(checkQuarto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return copaQuarto;
        }
    }

    @Override
    public List<CopaQuarto> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " quantidade,"
                + " data_hora_pedido,"
                + " obs,"
                + " status,"
                + " check_quarto_id"
                + " FROM copa_quarto"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CopaQuarto> listaCopaQuartos = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                CopaQuarto copaQuarto = new CopaQuarto();
                CheckQuarto checkQuarto = new CheckQuarto();
                
                copaQuarto.setId(rst.getInt("id"));
                copaQuarto.setQuantidade(rst.getFloat("quantidade"));
                copaQuarto.setDataHoraPedido(rst.getDate("data_hora_pedido"));
                copaQuarto.setObs(rst.getString("obs"));
                copaQuarto.setStatus(rst.getString("status").charAt(0));
                checkQuarto.setId(rst.getInt("check_quarto_id"));
                copaQuarto.setCheckQuarto(checkQuarto);
                
                listaCopaQuartos.add(copaQuarto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaCopaQuartos;
        }
    }

    @Override
    public void Update(CopaQuarto objeto) {
        String sqlInstrucao = "UPDATE copa_quarto "
                + " SET"
                + " quantidade =?,"
                + " data_hora_pedido=?,"
                + " obs=?,"
                + " status=?,"
                + " check_quarto_id=?,"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setFloat(1, objeto.getQuantidade());
            pstm.setDate(2, objeto.getDataHoraPedido());
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, objeto.getStatus()+"");
            pstm.setInt(5, objeto.getCheckQuarto().getId());
            pstm.setInt(6, objeto.getId());
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(CopaQuarto objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
