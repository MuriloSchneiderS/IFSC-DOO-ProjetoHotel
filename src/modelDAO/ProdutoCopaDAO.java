package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ProdutoCopa;
import java.util.List;

public class ProdutoCopaDAO implements InterfaceDAO<ProdutoCopa>{

    @Override
    public void Create(ProdutoCopa objeto) {
        String sqlInstrucao = "INSERT INTO produtoCopa("
                + " descricao,"
                + " valor,"
                + " obs,"
                + " status,"
                + " copa_quarto_id)"
                + " VALUES (?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getDescricao());
            pstm.setFloat(2, objeto.getValor());
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            pstm.setInt(5, objeto.getCopaQuartoId());
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public ProdutoCopa Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " descricao,"
                + " valor,"
                + " obs,"
                + " status,"
                + " copa_quarto_id"
                + " FROM produtoCopa WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ProdutoCopa produtoCopa = new ProdutoCopa();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                produtoCopa.setId(rst.getInt("id"));
                produtoCopa.setDescricao(rst.getString("descricao"));
                produtoCopa.setValor(rst.getFloat("valor"));
                produtoCopa.setObs(rst.getString("obs"));
                produtoCopa.setStatus(rst.getString("status").charAt(0));
                produtoCopa.setCopaQuartoId(rst.getInt("copa_quarto_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return produtoCopa;
        }
    }

    @Override
    public List<ProdutoCopa> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " descricao,"
                + " valor,"
                + " obs,"
                + " status,"
                + " copa_quarto_id"
                + " FROM produtoCopa"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<ProdutoCopa> listaProdutosCopa = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                ProdutoCopa produtoCopa = new ProdutoCopa();
                produtoCopa.setId(rst.getInt("id"));
                produtoCopa.setDescricao(rst.getString("descricao"));
                produtoCopa.setValor(rst.getFloat("valor"));
                produtoCopa.setObs(rst.getString("obs"));
                produtoCopa.setStatus(rst.getString("status").charAt(0));
                produtoCopa.setCopaQuartoId(rst.getInt("copa_quarto_id"));
                listaProdutosCopa.add(produtoCopa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaProdutosCopa;
        }
    }

    @Override
    public void Update(ProdutoCopa objeto) {
        String sqlInstrucao = "UPDATE produtoCopa "
                + " SET"
                + " descricao =?,"
                + " valor=?,"
                + " obs=?,"
                + " status=?,"
                + " copa_quarto_id=?,"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getDescricao());
            pstm.setFloat(2, objeto.getValor());
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, objeto.getStatus()+"");
            pstm.setInt(5, objeto.getCopaQuartoId());
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
    public void Delete(ProdutoCopa objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
