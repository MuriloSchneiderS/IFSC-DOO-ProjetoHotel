package service;

import java.util.List;
import model.bo.Hospede;
import model.DAO.HospedeDAO;

public class HospedeService{

    public static void Criar(Hospede objeto) {
        HospedeDAO.getInstance().Create(objeto);
    }

    public static Hospede Carregar(int id) {
        return HospedeDAO.getInstance().Retrieve(id);
    }

    public static List<Hospede> Carregar(String atributo, String valor) {
        return HospedeDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(Hospede objeto) {
        HospedeDAO.getInstance().Update(objeto);
    }

    public static void Apagar(Hospede objeto) {
        HospedeDAO.getInstance().Delete(objeto);
    }

}
