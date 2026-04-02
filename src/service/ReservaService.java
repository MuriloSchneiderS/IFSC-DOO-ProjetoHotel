package service;

import java.util.List;
import model.Reserva;
import modelDAO.ReservaDAO;

public class ReservaService{

    public static void Criar(Reserva objeto) {
        ReservaDAO checkDAO = new ReservaDAO();
        checkDAO.Create(objeto);
    }

    public static Reserva Carregar(int id) {
        ReservaDAO checkDAO = new ReservaDAO();
        return checkDAO.Retrieve(id);
    }

    public static List<Reserva> Carregar(String atributo, String valor) {
        ReservaDAO checkDAO = new ReservaDAO();
        return checkDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(Reserva objeto) {
        ReservaDAO checkDAO = new ReservaDAO();
        checkDAO.Update(objeto);
    }

    public static void Apagar(Reserva objeto) {
        ReservaDAO checkDAO = new ReservaDAO();
        checkDAO.Delete(objeto);
    }

}
