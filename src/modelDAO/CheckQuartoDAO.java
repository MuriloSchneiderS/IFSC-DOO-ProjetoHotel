package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CheckQuarto;
import model.Quarto;

public class CheckQuartoDAO implements InterfaceDAO<CheckQuarto> {

    @Override
    public void Create(CheckQuarto objeto) {
        String sqlInstrucao = "INSERT INTO check_quarto("
                + " data_hora_inicio,"
                + " data_hora_fim,"
                + " obs,"
                + " status,"
                + " quarto_id)"
                + " VALUES (?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraInicio()));
            pstm.setString(2, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraFim()));
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            pstm.setString(5, objeto.getQuarto().getId() + "");
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public CheckQuarto Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " data_hora_inicio,"
                + " data_hora_fim,"
                + " obs,"
                + " status,"
                + " quarto_id"
                + " FROM check_quarto WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CheckQuarto checkQuarto = new CheckQuarto();
        Quarto quarto = new Quarto();
        try {
            if (id < 0) {//Ultimo id inserido
                String sqlLast = "SELECT id, data_hora_inicio, data_hora_fim, obs, status, quarto_id FROM check_quarto ORDER BY id DESC LIMIT 1;";
                pstm = conexao.prepareStatement(sqlLast);
            } else {
                pstm = conexao.prepareStatement(sqlInstrucao);
                pstm.setInt(1, id);
            }
            rst = pstm.executeQuery();
            while (rst.next()) {
                checkQuarto.setId(rst.getInt("id"));
                checkQuarto.setDataHoraInicio(String.valueOf(rst.getTimestamp("data_hora_inicio")));
                checkQuarto.setDataHoraFim(String.valueOf(rst.getTimestamp("data_hora_fim")));
                checkQuarto.setObs(rst.getString("obs"));
                checkQuarto.setStatus(rst.getString("status").charAt(0));
                quarto.setId(rst.getInt("quarto_id"));
                checkQuarto.setQuarto(quarto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return checkQuarto;
        }
    }

    @Override
    public List<CheckQuarto> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " data_hora_inicio,"
                + " data_hora_fim,"
                + " obs,"
                + " status,"
                + " quarto_id"
                + " FROM check_quarto"
                + " WHERE " + atributo + " LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CheckQuarto> listaCheckQuartos = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                CheckQuarto checkQuarto = new CheckQuarto();
                Quarto quarto = new Quarto();

                checkQuarto.setId(rst.getInt("id"));
                checkQuarto.setDataHoraInicio(utilities.Utilities.formataDataHoraDeMySQL(String.valueOf(rst.getObject("data_hora_inicio", java.time.LocalDateTime.class).toString())));
                checkQuarto.setDataHoraFim(utilities.Utilities.formataDataHoraDeMySQL(String.valueOf(rst.getObject("data_hora_fim", java.time.LocalDateTime.class).toString())));
                checkQuarto.setObs(rst.getString("obs"));
                checkQuarto.setStatus(rst.getString("status").charAt(0));
                quarto.setId(rst.getInt("quarto_id"));
                checkQuarto.setQuarto(quarto);

                listaCheckQuartos.add(checkQuarto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaCheckQuartos;
        }
    }

    @Override
    public void Update(CheckQuarto objeto) {
        String sqlInstrucao = "UPDATE check_quarto "
                + " SET"
                + " data_hora_inicio =?,"
                + " data_hora_fim=?,"
                + " obs=?,"
                + " status=?,"
                + " quarto_id=?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraInicio()));
            pstm.setString(2, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraFim()));
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, objeto.getStatus() + "");
            pstm.setInt(5, objeto.getQuarto().getId());
            pstm.setString(6, objeto.getId() + "");
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(CheckQuarto objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
