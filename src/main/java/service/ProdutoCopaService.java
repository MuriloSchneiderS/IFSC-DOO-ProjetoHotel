package service;

import java.util.List;
import model.bo.ProdutoCopa;
import model.DAO.ProdutoCopaDAO;

public class ProdutoCopaService{

    public static void Criar(ProdutoCopa objeto) {
        ProdutoCopaDAO.getInstance().Create(objeto);
    }

    public static ProdutoCopa Carregar(int id) {
        return ProdutoCopaDAO.getInstance().Retrieve(id);
    }

    public static List<ProdutoCopa> Carregar(String atributo, String valor) {
        return ProdutoCopaDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(ProdutoCopa objeto) {
        ProdutoCopaDAO.getInstance().Update(objeto);
    }

    public static void Apagar(ProdutoCopa objeto) {
        ProdutoCopaDAO.getInstance().Delete(objeto);
    }

}
