package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.OrdemServico;
import java.util.List;
import model.Check;
import model.Quarto;
import model.Servico;

public class OrdemServicoDAO implements InterfaceDAO<OrdemServico>{

    @Override
    public void Create(OrdemServico objeto) {
        String sqlInstrucao = "INSERT INTO oderm_servico("//no banco esta oderm_servico
                + " data_hora_cadastro,"
                + " data_hora_prevista_inicio,"
                + " data_hora_prevista_termino,"
                + " obs,"
                + " status,"
                + " check_id,"
                + " servico_id,"
                + " quarto_id)"
                + " VALUES (?,?,?,?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, utilities.Utilities.formataDataParaMySQL(objeto.getDataHoraCadastro()));
            pstm.setString(2, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraPrevistaInicio()));
            pstm.setString(3, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraPrevistaTermino()));
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setString(6, objeto.getCheck().getId()+"");
            pstm.setString(7, objeto.getServico().getId()+"");
            pstm.setString(8, objeto.getQuarto().getId()+"");
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public OrdemServico Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " data_hora_cadastro,"
                + " data_hora_prevista_inicio,"
                + " data_hora_prevista_termino,"
                + " obs,"
                + " status,"
                + " check_id,"
                + " servico_id,"
                + " quarto_id"
                + " FROM oderm_servico WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        OrdemServico ordem_servico = new OrdemServico();
        Check check = new Check();
        Servico servico = new Servico();
        Quarto quarto = new Quarto();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                ordem_servico.setId(rst.getInt("id"));
                ordem_servico.setDataHoraCadastro(utilities.Utilities.formataDataHoraDeMySQL(rst.getObject("data_hora_cadastro", java.time.LocalDateTime.class).toString()));
                ordem_servico.setDataHoraPrevistaInicio(utilities.Utilities.formataDataHoraDeMySQL(rst.getObject("data_hora_prevista_inicio", java.time.LocalDateTime.class).toString()));
                ordem_servico.setDataHoraPrevistaTermino(utilities.Utilities.formataDataHoraDeMySQL(rst.getObject("data_hora_prevista_termino", java.time.LocalDateTime.class).toString()));
                ordem_servico.setObs(rst.getString("obs"));
                ordem_servico.setStatus(rst.getString("status").charAt(0));
                check = service.CheckService.Carregar(rst.getInt("check_id"));
                ordem_servico.setCheck(check);
                servico = service.ServicoService.Carregar(rst.getInt("servico_id"));
                ordem_servico.setServico(servico);
                quarto = service.QuartoService.Carregar(rst.getInt("quarto_id"));
                ordem_servico.setQuarto(quarto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return ordem_servico;
        }
    }

    @Override
    public List<OrdemServico> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " data_hora_cadastro,"
                + " data_hora_prevista_inicio,"
                + " data_hora_prevista_termino,"
                + " obs,"
                + " status,"
                + " check_id,"
                + " servico_id,"
                + " quarto_id"
                + " FROM oderm_servico"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<OrdemServico> listaOrdemServicos = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                OrdemServico ordem_servico = new OrdemServico();
                Check check = new Check();
                Servico servico = new Servico();
                Quarto quarto = new Quarto();
                ordem_servico.setId(rst.getInt("id"));
                ordem_servico.setDataHoraCadastro(utilities.Utilities.formataDataHoraDeMySQL(rst.getObject("data_hora_cadastro", java.time.LocalDateTime.class).toString()));
                ordem_servico.setDataHoraPrevistaInicio(utilities.Utilities.formataDataDeMySQL(rst.getObject("data_hora_prevista_inicio", java.time.LocalDateTime.class).toString()));
                ordem_servico.setDataHoraPrevistaTermino(utilities.Utilities.formataDataDeMySQL(rst.getObject("data_hora_prevista_termino", java.time.LocalDateTime.class).toString()));
                ordem_servico.setObs(rst.getString("obs"));
                ordem_servico.setStatus(rst.getString("status").charAt(0));
                check = service.CheckService.Carregar(rst.getInt("check_id"));
                ordem_servico.setCheck(check);
                servico = service.ServicoService.Carregar(rst.getInt("servico_id"));
                ordem_servico.setServico(servico);
                quarto = service.QuartoService.Carregar(rst.getInt("quarto_id"));
                ordem_servico.setQuarto(quarto);
                listaOrdemServicos.add(ordem_servico);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaOrdemServicos;
        }
    }

    @Override
    public void Update(OrdemServico objeto) {
        String sqlInstrucao = "UPDATE ordem_servico "
                + " SET"
                + " data_hora_cadastro=?,"
                + " data_hora_prevista_inicio=?,"
                + " data_hora_prevista_termino=?,"
                + " obs=?,"
                + " status=?,"
                + " check_id=?,"
                + " servico_id=?,"
                + " quarto_id=?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, utilities.Utilities.formataDataParaMySQL(objeto.getDataHoraCadastro()));
            pstm.setString(2, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraPrevistaInicio()));
            pstm.setString(3, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraPrevistaTermino()));
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setString(6, objeto.getCheck().getId()+"");
            pstm.setString(7, objeto.getServico().getId()+"");
            pstm.setString(8, objeto.getQuarto().getId()+"");
            pstm.setString(9, objeto.getId()+"");
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(OrdemServico objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
