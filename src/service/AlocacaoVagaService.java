package service;

import java.util.List;
import model.AlocacaoVaga;
import modelDAO.AlocacaoVagaDAO;

public class AlocacaoVagaService{

    public static void Criar(AlocacaoVaga objeto) {
        AlocacaoVagaDAO alocacaoVagaDAO = new AlocacaoVagaDAO();
        alocacaoVagaDAO.Create(objeto);
    }

    public static AlocacaoVaga Carregar(int id) {
        AlocacaoVagaDAO alocacaoVagaDAO = new AlocacaoVagaDAO();
        return alocacaoVagaDAO.Retrieve(id);
    }

    public static List<AlocacaoVaga> Carregar(String atributo, String valor) {
        AlocacaoVagaDAO alocacaoVagaDAO = new AlocacaoVagaDAO();
        return alocacaoVagaDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(AlocacaoVaga objeto) {
        AlocacaoVagaDAO alocacaoVagaDAO = new AlocacaoVagaDAO();
        alocacaoVagaDAO.Update(objeto);
    }

    public static void Apagar(AlocacaoVaga objeto) {
        AlocacaoVagaDAO alocacaoVagaDAO = new AlocacaoVagaDAO();
        alocacaoVagaDAO.Delete(objeto);
    }

}
