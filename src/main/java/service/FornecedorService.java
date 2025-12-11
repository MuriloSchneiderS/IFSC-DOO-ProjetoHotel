package service;

import java.util.List;
import model.bo.Fornecedor;
import model.DAO.FornecedorDAO;

public class FornecedorService{

    public static void Criar(Fornecedor objeto) {
        FornecedorDAO.getInstance().Create(objeto);
    }

    public static Fornecedor Carregar(int id) {
        return FornecedorDAO.getInstance().Retrieve(id);
    }

    public static List<Fornecedor> Carregar(String atributo, String valor) {
        return FornecedorDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(Fornecedor objeto) {
        FornecedorDAO.getInstance().Update(objeto);
    }

    public static void Apagar(Fornecedor objeto) {
        FornecedorDAO.getInstance().Delete(objeto);
    }

}
