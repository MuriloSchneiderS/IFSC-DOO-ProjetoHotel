package modelDAO;

import model.Funcionario;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FuncionarioDAO implements InterfaceDAO<Funcionario>{

    @Override
    public void Create(Funcionario objeto) {
        String sqlInstrucao = "INSERT INTO funcionario("
                + " nome,"
                + " fone,"
                + " fone2,"
                + " email,"
                + " cep,"
                + " logradouro,"
                + " bairro,"
                + " cidade,"
                + " complemento,"
                + " data_cadastro,"
                + " cpf,"
                + " rg,"
                + " obs,"
                + " status,"
                + " usuario,"
                + " senha)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone1());
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            pstm.setNull(10, java.sql.Types.DATE);
            pstm.setString(11, objeto.getCpf());
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getUsuario());
            pstm.setString(16, objeto.getSenha());
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Funcionario Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " nome,"
                + " fone,"
                + " fone2,"
                + " email,"
                + " cep,"
                + " logradouro,"
                + " bairro,"
                + " cidade,"
                + " complemento,"
                + " data_cadastro,"
                + " cpf,"
                + " rg,"
                + " obs,"
                + " status,"
                + " usuario,"
                + " senha"
                + " FROM funcionario WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Funcionario funcionario = new Funcionario();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                funcionario.setId(rst.getInt("id"));
                funcionario.setNome(rst.getString(2));
                funcionario.setFone1(rst.getString("fone"));
                funcionario.setFone2(rst.getString("fone2"));
                funcionario.setEmail(rst.getString("email"));
                funcionario.setCep(rst.getString("cep"));
                funcionario.setLogradouro(rst.getString("logradouro"));
                funcionario.setBairro(rst.getString("bairro"));
                funcionario.setCidade(rst.getString("cidade"));
                funcionario.setComplemento(rst.getString("complemento"));
                funcionario.setDataCadastro(String.valueOf(rst.getDate("data_cadastro")));
                funcionario.setCpf(rst.getString("cpf"));
                funcionario.setRg(rst.getString("rg"));
                funcionario.setObs(rst.getString("obs"));
                funcionario.setStatus(rst.getString("status").charAt(0));
                funcionario.setUsuario(rst.getString("usuario"));
                funcionario.setSenha(rst.getString("senha"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return funcionario;
        }
    }

    @Override
    public List<Funcionario> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " nome,"
                + " fone,"
                + " fone2,"
                + " email,"
                + " cep,"
                + " logradouro,"
                + " bairro,"
                + " cidade,"
                + " complemento,"
                + " data_cadastro,"
                + " cpf,"
                + " rg,"
                + " obs,"
                + " status,"
                + " usuario,"
                + " senha"
                + " FROM funcionario"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (!rst.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rst.getInt("id"));
                funcionario.setNome(rst.getString(2));
                funcionario.setFone1(rst.getString("fone"));
                funcionario.setFone2(rst.getString("fone2"));
                funcionario.setEmail(rst.getString("email"));
                funcionario.setCep(rst.getString("cep"));
                funcionario.setLogradouro(rst.getString("logradouro"));
                funcionario.setBairro(rst.getString("bairro"));
                funcionario.setCidade(rst.getString("cidade"));
                funcionario.setComplemento(rst.getString("complemento"));
                funcionario.setDataCadastro(String.valueOf(rst.getDate("data_cadastro")));
                funcionario.setCpf(rst.getString("cpf"));
                funcionario.setRg(rst.getString("rg"));
                funcionario.setObs(rst.getString("obs"));
                funcionario.setStatus(rst.getString("status").charAt(0));
                funcionario.setUsuario(rst.getString("usuario"));
                funcionario.setSenha(rst.getString("senha"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaFuncionarios;
        }
    }

    @Override
    public void Update(Funcionario objeto) {
        String sqlInstrucao = "UPDATE funcionario "
                + " SET"
                + " nome =?,"
                + " fone =?"
                + " fone2 =?"
                + " email =?"
                + " cep =?"
                + " logradouro =?"
                + " bairro =?"
                + " cidade =?"
                + " complemento =?"
                + " data_cadastro =?"
                + " cpf =?"
                + " rg =?"
                + " obs =?"
                + " status =?"
                + " usuario =?"
                + " senha =?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone1());
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            pstm.setNull(10, java.sql.Types.DATE);
            pstm.setString(11, objeto.getCpf());
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getUsuario());
            pstm.setString(16, objeto.getSenha());
            pstm.setInt(17, objeto.getId());
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(Funcionario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
