package service;

import java.util.List;
import model.bo.Servico;
import model.DAO.ServicoDAO;

public class ServicoService{

    public static void Criar(Servico objeto) {
        ServicoDAO.getInstance().Create(objeto);
    }

    public static Servico Carregar(int id) {
        return ServicoDAO.getInstance().Retrieve(id);
    }

    public static List<Servico> Carregar(String atributo, String valor) {
        return ServicoDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(Servico objeto) {
        ServicoDAO.getInstance().Update(objeto);
    }

    public static void Apagar(Servico objeto) {
        ServicoDAO.getInstance().Delete(objeto);
    }

}
