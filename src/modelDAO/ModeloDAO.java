package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Modelo;
import java.util.List;
import model.Marca;

public class ModeloDAO implements InterfaceDAO<Modelo>{

    @Override
    public void Create(Modelo objeto) {
        String sqlInstrucao = "INSERT INTO modelo("
                + " descricao,"
                + " status,"
                + " marca_id)"
                + " VALUES (?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, String.valueOf(objeto.getStatus()));
            pstm.setString(3, Integer.toString(objeto.getMarca().getId()));
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Modelo Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " descricao,"
                + " status,"
                + " marca_id"
                + " FROM modelo WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Modelo modelo = new Modelo();
        Marca marca = new Marca();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                modelo.setId(rst.getInt("id"));
                modelo.setDescricao(rst.getString(2));
                modelo.setStatus(rst.getString("status").charAt(0));
                marca.setId(rst.getInt("marca_id"));
                modelo.setMarca(marca);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return modelo;
        }
    }

    @Override
    public List<Modelo> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " descricao,"
                + " status,"
                + " marca_id"
                + " FROM modelo"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Modelo> listaModelos = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (!rst.next()) {
                Modelo modelo = new Modelo();
                Marca marca = new Marca();
                modelo.setId(rst.getInt("id"));
                modelo.setDescricao(rst.getString(2));
                modelo.setStatus(rst.getString("status").charAt(0));
                marca.setId(rst.getInt("marca_id"));
                modelo.setMarca(marca);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaModelos;
        }
    }

    @Override
    public void Update(Modelo objeto) {
        String sqlInstrucao = "UPDATE modelo "
                + " SET"
                + " descricao =?,"
                + " status =?,"
                + " marca_id=?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, String.valueOf(objeto.getStatus()));
            pstm.setInt(3, objeto.getMarca().getId());
            pstm.setInt(4, objeto.getId());
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(Modelo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
