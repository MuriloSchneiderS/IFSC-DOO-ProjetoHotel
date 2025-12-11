package service;

import java.util.List;
import model.bo.CheckQuarto;
import model.DAO.CheckQuartoDAO;

public class CheckQuartoService{

    public static void Criar(CheckQuarto objeto) {
        CheckQuartoDAO.getInstance().Create(objeto);
    }

    public static CheckQuarto Carregar(int id) {
        return CheckQuartoDAO.getInstance().Retrieve(id);
    }

    public static List<CheckQuarto> Carregar(String atributo, String valor) {
        return CheckQuartoDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(CheckQuarto objeto) {
        CheckQuartoDAO.getInstance().Update(objeto);
    }

    public static void Apagar(CheckQuarto objeto) {
        CheckQuartoDAO.getInstance().Delete(objeto);
    }

}
