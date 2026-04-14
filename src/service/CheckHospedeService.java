package service;

import java.util.List;
import model.CheckHospede;
import modelDAO.CheckHospedeDAO;

public class CheckHospedeService{

    public static void Criar(CheckHospede objeto) {
        CheckHospedeDAO checkHospedeDAO = new CheckHospedeDAO();
        checkHospedeDAO.Create(objeto);
    }

    public static CheckHospede Carregar(int id) {
        CheckHospedeDAO checkHospedeDAO = new CheckHospedeDAO();
        return checkHospedeDAO.Retrieve(id);
    }

    public static List<CheckHospede> Carregar(String atributo, String valor) {
        CheckHospedeDAO checkHospedeDAO = new CheckHospedeDAO();
        return checkHospedeDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(CheckHospede objeto) {
        CheckHospedeDAO checkHospedeDAO = new CheckHospedeDAO();
        checkHospedeDAO.Update(objeto);
    }

    public static void Apagar(CheckHospede objeto) {
        CheckHospedeDAO checkHospedeDAO = new CheckHospedeDAO();
        checkHospedeDAO.Delete(objeto);
    }

}
