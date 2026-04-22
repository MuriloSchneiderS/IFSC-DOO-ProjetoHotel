package service;

import java.util.List;
import model.OrdemServico;
import modelDAO.OrdemServicoDAO;

public class OrdemServicoService{

    public static void Criar(OrdemServico objeto) {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        ordemServicoDAO.Create(objeto);
    }

    public static OrdemServico Carregar(int id) {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        return ordemServicoDAO.Retrieve(id);
    }

    public static List<OrdemServico> Carregar(String atributo, String valor) {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        return ordemServicoDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(OrdemServico objeto) {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        ordemServicoDAO.Update(objeto);
    }

    public static void Apagar(OrdemServico objeto) {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        ordemServicoDAO.Delete(objeto);
    }

}
