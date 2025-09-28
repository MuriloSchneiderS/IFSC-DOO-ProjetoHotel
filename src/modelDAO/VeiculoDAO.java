package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Veiculo;
import java.util.List;
import model.Fornecedor;
import model.Funcionario;
import model.Hospede;
import model.Modelo;

public class VeiculoDAO implements InterfaceDAO<Veiculo>{

    @Override
    public void Create(Veiculo objeto) {
        String sqlInstrucao = "INSERT INTO veiculo("
                + " placa,"
                + " cor,"
                + " modelo_id,"
                + " funcionario_id,"
                + " fornecedor_id,"
                + " hospede_id,"
                + " status)"
                + " VALUES (?,?,?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            pstm.setString(3, Integer.toString(objeto.getModelo().getId()));
            pstm.setString(4, Integer.toString(objeto.getFuncionario().getId()));
            pstm.setString(5, Integer.toString(objeto.getFornecedor().getId()));
            pstm.setString(6, Integer.toString(objeto.getHospede().getId()));
            pstm.setString(7, String.valueOf(objeto.getStatus()));
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Veiculo Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " placa,"
                + " cor,"
                + " modelo_id,"
                + " funcionario_id,"
                + " fornecedor_id,"
                + " hospede_id,"
                + " status"
                + " FROM veiculo WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Veiculo veiculo = new Veiculo();
        Modelo modelo = new Modelo();
        Funcionario funcionario = new Funcionario();
        Fornecedor fornecedor = new Fornecedor();
        Hospede hospede = new Hospede();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                modelo.setId(rst.getInt("modelo_id"));
                veiculo.setModelo(modelo);
                funcionario.setId(rst.getInt("funcionario_id"));
                veiculo.setFuncionario(funcionario);
                fornecedor.setId(rst.getInt("fornecedor_id"));
                veiculo.setFornecedor(fornecedor);
                hospede.setId(rst.getInt("hospede_id"));
                veiculo.setHospede(hospede);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return veiculo;
        }
    }

    @Override
    public List<Veiculo> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " placa,"
                + " cor,"
                + " modelo_id,"
                + " funcionario_id,"
                + " fornecedor_id,"
                + " hospede_id,"
                + " status"
                + " FROM veiculo"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Veiculo> listaVeiculos = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (!rst.next()) {
                Veiculo veiculo = new Veiculo();
                Modelo modelo = new Modelo();
                Funcionario funcionario = new Funcionario();
                Fornecedor fornecedor = new Fornecedor();
                Hospede hospede = new Hospede();
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                modelo.setId(rst.getInt("modelo_id"));
                veiculo.setModelo(modelo);
                funcionario.setId(rst.getInt("funcionario_id"));
                veiculo.setFuncionario(funcionario);
                fornecedor.setId(rst.getInt("fornecedor_id"));
                veiculo.setFornecedor(fornecedor);
                hospede.setId(rst.getInt("hospede_id"));
                veiculo.setHospede(hospede);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaVeiculos;
        }
    }

    @Override
    public void Update(Veiculo objeto) {
        String sqlInstrucao = "UPDATE veiculo"
                + " SET"
                + " placa=?,"
                + " cor=?,"
                + " modelo_id=?,"
                + " funcionario_id=?,"
                + " fornecedor_id=?,"
                + " hospede_id=?,"
                + " status =?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            pstm.setInt(3, objeto.getModelo().getId());
            pstm.setInt(4, objeto.getFuncionario().getId());
            pstm.setInt(5, objeto.getFornecedor().getId());
            pstm.setInt(6, objeto.getHospede().getId());
            pstm.setString(7, String.valueOf(objeto.getStatus()));
            pstm.setInt(8, objeto.getId());
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(Veiculo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
