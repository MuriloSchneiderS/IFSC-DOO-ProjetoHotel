package service;

import java.util.List;
import model.CheckQuarto;
import modelDAO.CheckQuartoDAO;

public class CheckQuartoService{

    public static void Criar(CheckQuarto objeto) {
        CheckQuartoDAO checkQuartoDAO = new CheckQuartoDAO();
        checkQuartoDAO.Create(objeto);
    }

    public static CheckQuarto Carregar(int id) {
        CheckQuartoDAO checkQuartoDAO = new CheckQuartoDAO();
        return checkQuartoDAO.Retrieve(id);
    }

    public static List<CheckQuarto> Carregar(String atributo, String valor) {
        CheckQuartoDAO checkQuartoDAO = new CheckQuartoDAO();
        return checkQuartoDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(CheckQuarto objeto) {
        CheckQuartoDAO checkQuartoDAO = new CheckQuartoDAO();
        checkQuartoDAO.Update(objeto);
    }

    public static void Apagar(CheckQuarto objeto) {
        CheckQuartoDAO checkQuartoDAO = new CheckQuartoDAO();
        checkQuartoDAO.Delete(objeto);
    }

}
