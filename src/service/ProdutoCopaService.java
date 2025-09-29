package service;

import java.util.List;
import model.ProdutoCopa;
import modelDAO.ProdutoCopaDAO;

public class ProdutoCopaService{

    public static void Criar(ProdutoCopa objeto) {
        ProdutoCopaDAO produtoCopaDAO = new ProdutoCopaDAO();
        produtoCopaDAO.Create(objeto);
    }

    public static ProdutoCopa Carregar(int id) {
        ProdutoCopaDAO produtoCopaDAO = new ProdutoCopaDAO();
        return produtoCopaDAO.Retrieve(id);
    }

    public static List<ProdutoCopa> Carregar(String atributo, String valor) {
        ProdutoCopaDAO produtoCopaDAO = new ProdutoCopaDAO();
        return produtoCopaDAO.Retrieve(atributo, valor);
    }

    public static void Atualizar(ProdutoCopa objeto) {
        ProdutoCopaDAO produtoCopaDAO = new ProdutoCopaDAO();
        produtoCopaDAO.Update(objeto);
    }

    public static void Apagar(ProdutoCopa objeto) {
        ProdutoCopaDAO produtoCopaDAO = new ProdutoCopaDAO();
        produtoCopaDAO.Delete(objeto);
    }

}
