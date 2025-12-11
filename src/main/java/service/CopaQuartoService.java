package service;

import java.util.List;
import model.bo.CopaQuarto;
import model.DAO.CopaQuartoDAO;

public class CopaQuartoService{

    public static void Criar(CopaQuarto objeto) {
        CopaQuartoDAO.getInstance().Create(objeto);
    }

    public static CopaQuarto Carregar(int id) {
        return CopaQuartoDAO.getInstance().Retrieve(id);
    }

    public static List<CopaQuarto> Carregar(String atributo, String valor) {
        return CopaQuartoDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(CopaQuarto objeto) {
        CopaQuartoDAO.getInstance().Update(objeto);
    }

    public static void Apagar(CopaQuarto objeto) {
        CopaQuartoDAO.getInstance().Delete(objeto);
    }

}
