package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Reserva;
import java.util.List;
import model.Check;

public class ReservaDAO implements InterfaceDAO<Reserva>{

    @Override
    public void Create(Reserva objeto) {
        String sqlInstrucao = "INSERT INTO reserva("
                + " data_hora_reserva,"
                + " data_prevista_entrada,"
                + " data_prevista_saida,"
                + " obs,"
                + " status,"
                + " check_id)"
                + " VALUES (?,?,?,?,?,?)";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, utilities.Utilities.formataDataParaMySQL(objeto.getDataHoraReserva()));
            pstm.setString(2, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataPrevistaEntrada()));
            pstm.setString(3, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataPrevistaSaida()));
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setString(6, objeto.getCheck().get(0).getId()+"");
            pstm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Reserva Retrieve(int id) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " data_hora_reserva,"
                + " data_prevista_entrada,"
                + " data_prevista_saida,"
                + " obs,"
                + " status,"
                + " check_id"
                + " FROM reserva WHERE id=?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Reserva reserva = new Reserva();
        Check check = new Check();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            while (rst.next()) {
                reserva.setId(rst.getInt("id"));
                reserva.setDataHoraReserva(String.valueOf(rst.getDate("data_hora_reserva")));
                reserva.setDataPrevistaEntrada(String.valueOf(rst.getDate("data_prevista_entrada")));
                reserva.setDataPrevistaSaida(String.valueOf(rst.getDate("data_prevista_saida")));
                reserva.setObs(rst.getString("obs"));
                reserva.setStatus(rst.getString("status").charAt(0));
                check.setId(rst.getInt("check_id"));
                reserva.setCheck((List<Check>) check);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return reserva;
        }
    }

    @Override
    public List<Reserva> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT"
                + " id,"
                + " data_hora_reserva,"
                + " data_prevista_entrada,"
                + " data_prevista_saida,"
                + " obs,"
                + " status,"
                + " check_id"
                + " FROM reserva"
                + " WHERE "+atributo+" LIKE ?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Reserva> listaReservas = new ArrayList<>();
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%"+valor+"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                Reserva reserva = new Reserva();
                Check check = new Check();
                reserva.setDataHoraReserva(String.valueOf(rst.getDate("data_hora_reserva")));
                reserva.setDataPrevistaEntrada(String.valueOf(rst.getDate("data_prevista_entrada")));
                reserva.setDataPrevistaSaida(String.valueOf(rst.getDate("data_prevista_saida")));
                reserva.setObs(rst.getString("obs"));
                reserva.setStatus(rst.getString("status").charAt(0));
                check.setId(rst.getInt("check_id"));
                reserva.setCheck((List<Check>) check);
                listaReservas.add(reserva);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaReservas;
        }
    }

    @Override
    public void Update(Reserva objeto) {
        String sqlInstrucao = "UPDATE reserva "
                + " SET"
                + " data_hora_reserva=?,"
                + " data_prevista_entrada=?,"
                + " data_prevista_saida=?,"
                + " obs=?,"
                + " status=?,"
                + " check_id=?"
                + " WHERE id =?";
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, utilities.Utilities.formataDataParaMySQL(objeto.getDataHoraReserva()));
            pstm.setString(2, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataPrevistaEntrada()));
            pstm.setString(3, utilities.Utilities.formataDataHoraParaMySQL(objeto.getDataPrevistaSaida()));
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setString(6, objeto.getCheck().get(0).getId()+"");
            pstm.execute();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
            return;
        }
    }

    @Override
    public void Delete(Reserva objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
