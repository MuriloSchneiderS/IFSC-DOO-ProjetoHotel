package service;

import java.util.List;
import model.Check;
import modelDAO.CheckDAO;

public class CheckService{

    public static void Criar(Check objeto) {
        CheckDAO checkDAO = new CheckDAO();
        checkDAO.Create(objeto);
    }

    public static Check Carregar(int id) {
        CheckDAO checkDAO = new CheckDAO();
        return checkDAO.Retrieve(id);
    }

    public static List<Check> Carregar(String atributo, String valor) {
        CheckDAO checkDAO = new CheckDAO();
        return checkDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(Check objeto) {
        CheckDAO checkDAO = new CheckDAO();
        checkDAO.Update(objeto);
    }

    public static void Apagar(Check objeto) {
        CheckDAO checkDAO = new CheckDAO();
        checkDAO.Delete(objeto);
    }

}
