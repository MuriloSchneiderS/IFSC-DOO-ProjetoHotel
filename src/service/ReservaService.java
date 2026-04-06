package service;

import java.util.List;
import model.Reserva;
import modelDAO.ReservaDAO;

public class ReservaService{

    public static void Criar(Reserva objeto) {
        ReservaDAO reservaDAO = new ReservaDAO();
        reservaDAO.Create(objeto);
    }

    public static Reserva Carregar(int id) {
        ReservaDAO reservaDAO = new ReservaDAO();
        return reservaDAO.Retrieve(id);
    }

    public static List<Reserva> Carregar(String atributo, String valor) {
        ReservaDAO reservaDAO = new ReservaDAO();
        return reservaDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(Reserva objeto) {
        ReservaDAO reservaDAO = new ReservaDAO();
        reservaDAO.Update(objeto);
    }

    public static void Apagar(Reserva objeto) {
        ReservaDAO reservaDAO = new ReservaDAO();
        reservaDAO.Delete(objeto);
    }

}
