package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ReservaQuarto;
import java.util.List;
import model.Check;
import model.Quarto;
import model.Reserva;

public class ReservaQuartoDAO implements InterfaceDAO<ReservaQuarto>{

    @Override
    public void Create(ReservaQuarto objeto) {
        String sqlInstrucao = "INSERT INTO reserva_quarto("
                + " data_hora_inicio,"
                + " data_hora_fim,"
                + " obs,"
                + " status,"
                + " reserva_id,"
                + " quarto_id)"
                + " VALUES (?,?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraInicio()));
            pstm.setString(2, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraFim()));
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            pstm.setString(5, objeto.getReserva().getId()+"");
            pstm.setString(6, objeto.getQuarto().getId()+"");
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public ReservaQuarto Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " data_hora_inicio,"
                + " data_hora_fim,"
                + " obs,"
                + " status,"
                + " reserva_id,"
                + " quarto_id"
                + " FROM reserva_quarto WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ReservaQuarto reserva_quarto = new ReservaQuarto();
        Reserva reserva = new Reserva();
        Quarto quarto = new Quarto();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                reserva_quarto.setId(rst.getInt("id"));
                reserva_quarto.setDataHoraInicio(String.valueOf(rst.getDate("data_hora_inicio")));
                reserva_quarto.setDataHoraFim(String.valueOf(rst.getDate("data_hora_fim")));
                reserva_quarto.setObs(rst.getString("obs"));
                reserva_quarto.setStatus(rst.getString("status").charAt(0));
                reserva.setId(rst.getInt("reserva_id"));
                reserva_quarto.setReserva(reserva);
                quarto.setId(rst.getInt("quarto_id"));
                reserva_quarto.setQuarto(quarto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return reserva_quarto;
        }
    }

    @Override
    public List<ReservaQuarto> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " data_hora_inicio,"
                + " data_hora_fim,"
                + " obs,"
                + " status,"
                + " reserva_id,"
                + " quarto_id"
                + " FROM reserva_quarto"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<ReservaQuarto> listaReservaQuartos = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                ReservaQuarto reserva_quarto = new ReservaQuarto();
                Reserva reserva = new Reserva();
                Quarto quarto = new Quarto();
                reserva_quarto.setId(rst.getInt("id"));
                reserva_quarto.setDataHoraInicio(String.valueOf(rst.getDate("data_hora_inicio")));
                reserva_quarto.setDataHoraFim(String.valueOf(rst.getDate("data_hora_fim")));
                reserva_quarto.setObs(rst.getString("obs"));
                reserva_quarto.setStatus(rst.getString("status").charAt(0));
                reserva.setId(rst.getInt("reserva_id"));
                reserva_quarto.setReserva(reserva);
                quarto.setId(rst.getInt("quarto_id"));
                reserva_quarto.setQuarto(quarto);
                listaReservaQuartos.add(reserva_quarto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaReservaQuartos;
        }
    }

    @Override
    public void Update(ReservaQuarto objeto) {
        String sqlInstrucao = "UPDATE reserva_quarto "
                + " SET"
                + " data_hora_inicio=?,"
                + " data_hora_fim=?,"
                + " obs=?,"
                + " status=?,"
                + " reserva_id=?,"
                + " quarto_id=?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraInicio()));
            pstm.setString(2, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataHoraFim()));
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            pstm.setString(5, objeto.getReserva().getId()+"");
            pstm.setString(6, objeto.getQuarto().getId()+"");
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(ReservaQuarto objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
