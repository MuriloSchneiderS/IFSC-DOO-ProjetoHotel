package service;

import java.util.List;
import model.bo.Quarto;
import model.DAO.QuartoDAO;

public class QuartoService{

    public static void Criar(Quarto objeto) {
        QuartoDAO.getInstance().Create(objeto);
    }

    public static Quarto Carregar(int id) {
        return QuartoDAO.getInstance().Retrieve(id);
    }

    public static List<Quarto> Carregar(String atributo, String valor) {
        return QuartoDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(Quarto objeto) {
        QuartoDAO.getInstance().Update(objeto);
    }

    public static void Apagar(Quarto objeto) {
        QuartoDAO.getInstance().Delete(objeto);
    }

}
