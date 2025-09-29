package service;

import java.util.List;
import model.Quarto;
import modelDAO.QuartoDAO;

public class QuartoService{

    public static void Criar(Quarto objeto) {
        QuartoDAO quartoDAO = new QuartoDAO();
        quartoDAO.Create(objeto);
    }

    public static Quarto Carregar(int id) {
        QuartoDAO quartoDAO = new QuartoDAO();
        return quartoDAO.Retrieve(id);
    }

    public static List<Quarto> Carregar(String atributo, String valor) {
        QuartoDAO quartoDAO = new QuartoDAO();
        return quartoDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(Quarto objeto) {
        QuartoDAO quartoDAO = new QuartoDAO();
        quartoDAO.Update(objeto);
    }

    public static void Apagar(Quarto objeto) {
        QuartoDAO quartoDAO = new QuartoDAO();
        quartoDAO.Delete(objeto);
    }

}
