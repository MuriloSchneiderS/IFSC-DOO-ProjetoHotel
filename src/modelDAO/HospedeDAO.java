package modelDAO;

import model.Hospede;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HospedeDAO implements InterfaceDAO<Hospede> {

    @Override
    public void Create(Hospede objeto) {
        String sqlInstrucao = "INSERT INTO hospede("
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
                + " senha,"
                + " razao_social,"
                + " cnpj,"
                + " inscricao_estadual,"
                + " contato)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pstm.setString(10, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataCadastro())));
            pstm.setString(11, objeto.getCpf());
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, objeto.getStatus()+"");
            pstm.setString(15, objeto.getUsuario());
            pstm.setString(16, objeto.getSenha());
            pstm.setString(17, objeto.getRazaoSocial());
            pstm.setString(18, objeto.getCnpj());
            pstm.setString(19, objeto.getInscricaoEstadual());
            pstm.setString(20, objeto.getContato());
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(HospedeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Hospede Retrieve(int id) {
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
                + " senha,"
                + " razao_social,"
                + " cnpj,"
                + " inscricao_estadual,"
                + " contato"
                + " FROM hospede WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Hospede hospede = new Hospede();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                hospede.setId(rst.getInt("id"));
                hospede.setNome(rst.getString(2));
                hospede.setFone1(rst.getString("fone"));
                hospede.setFone2(rst.getString("fone2"));
                hospede.setEmail(rst.getString("email"));
                hospede.setCep(rst.getString("cep"));
                hospede.setLogradouro(rst.getString("logradouro"));
                hospede.setBairro(rst.getString("bairro"));
                hospede.setCidade(rst.getString("cidade"));
                hospede.setComplemento(rst.getString("complemento"));
                hospede.setDataCadastro(String.valueOf(rst.getDate("data_cadastro")));
                hospede.setCpf(rst.getString("cpf"));
                hospede.setRg(rst.getString("rg"));
                hospede.setObs(rst.getString("obs"));
                hospede.setStatus(rst.getString("status").charAt(0));
                hospede.setUsuario(rst.getString("usuario"));
                hospede.setSenha(rst.getString("senha"));
                hospede.setRazaoSocial(rst.getString("razao_social"));
                hospede.setCnpj(rst.getString("cnpj"));
                hospede.setInscricaoEstadual(rst.getString("inscricao_estadual"));
                hospede.setContato(rst.getString("contato"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return hospede;
        }
    }

    @Override
    public List<Hospede> Retrieve(String atributo, String valor) {
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
                + " senha,"
                + " razao_social,"
                + " cnpj,"
                + " inscricao_estadual,"
                + " contato"
                + " FROM hospede"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Hospede> listaHospedes = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                Hospede hospede = new Hospede();
                hospede.setId(rst.getInt("id"));
                hospede.setNome(rst.getString(2));
                hospede.setFone1(rst.getString("fone"));
                hospede.setFone2(rst.getString("fone2"));
                hospede.setEmail(rst.getString("email"));
                hospede.setCep(rst.getString("cep"));
                hospede.setLogradouro(rst.getString("logradouro"));
                hospede.setBairro(rst.getString("bairro"));
                hospede.setCidade(rst.getString("cidade"));
                hospede.setComplemento(rst.getString("complemento"));
                hospede.setDataCadastro(String.valueOf(rst.getDate("data_cadastro")));
                hospede.setCpf(rst.getString("cpf"));
                hospede.setRg(rst.getString("rg"));
                hospede.setObs(rst.getString("obs"));
                hospede.setStatus(rst.getString("status").charAt(0));
                hospede.setUsuario(rst.getString("usuario"));
                hospede.setSenha(rst.getString("senha"));
                hospede.setRazaoSocial(rst.getString("razao_social"));
                hospede.setCnpj(rst.getString("cnpj"));
                hospede.setInscricaoEstadual(rst.getString("inscricao_estadual"));
                hospede.setContato(rst.getString("contato"));
                listaHospedes.add(hospede);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaHospedes;
        }
    }

    @Override
    public void Update(Hospede objeto) {
        String sqlInstrucao = "UPDATE hospede "
                + " SET"
                + " nome =?,"
                + " fone =?,"
                + " fone2 =?,"
                + " email =?,"
                + " cep =?,"
                + " logradouro =?,"
                + " bairro =?,"
                + " cidade =?,"
                + " complemento =?,"
                + " data_cadastro =?,"
                + " cpf =?,"
                + " rg =?,"
                + " obs =?,"
                + " status =?,"
                + " usuario=?,"
                + " senha=?,"
                + " razao_social =?,"
                + " cnpj =?,"
                + " inscricao_estadual =?,"
                + " contato =?"
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
            pstm.setString(10, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataCadastro())));
            pstm.setString(11, objeto.getCpf());
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getUsuario());
            pstm.setString(16, objeto.getSenha());
            pstm.setString(17, objeto.getRazaoSocial());
            pstm.setString(18, objeto.getCnpj());
            pstm.setString(19, objeto.getInscricaoEstadual());
            pstm.setString(20, objeto.getContato());
            pstm.setString(21, objeto.getId()+"");
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(Hospede objeto) {
         
    }

}
