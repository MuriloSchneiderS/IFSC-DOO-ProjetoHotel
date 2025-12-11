package service;

import java.util.List;
import model.CopaQuarto;
import modelDAO.CopaQuartoDAO;

public class CopaQuartoService{

    public static void Criar(CopaQuarto objeto) {
        CopaQuartoDAO copaQuartoDAO = new CopaQuartoDAO();
        copaQuartoDAO.Create(objeto);
    }

    public static CopaQuarto Carregar(int id) {
        CopaQuartoDAO copaQuartoDAO = new CopaQuartoDAO();
        return copaQuartoDAO.Retrieve(id);
    }

    public static List<CopaQuarto> Carregar(String atributo, String valor) {
        CopaQuartoDAO copaQuartoDAO = new CopaQuartoDAO();
        return copaQuartoDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(CopaQuarto objeto) {
        CopaQuartoDAO copaQuartoDAO = new CopaQuartoDAO();
        copaQuartoDAO.Update(objeto);
    }

    public static void Apagar(CopaQuarto objeto) {
        CopaQuartoDAO copaQuartoDAO = new CopaQuartoDAO();
        copaQuartoDAO.Delete(objeto);
    }

}
