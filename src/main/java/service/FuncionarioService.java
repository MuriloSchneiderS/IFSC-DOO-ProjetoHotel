package service;

import java.util.List;
import model.bo.Funcionario;
import model.DAO.FuncionarioDAO;

public class FuncionarioService{

    public static void Criar(Funcionario objeto) {
        FuncionarioDAO.getInstance().Create(objeto);
    }

    public static Funcionario Carregar(int id) {
        return FuncionarioDAO.getInstance().Retrieve(id);
    }

    public static List<Funcionario> Carregar(String atributo, String valor) {
        return FuncionarioDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(Funcionario objeto) {
        FuncionarioDAO.getInstance().Update(objeto);
    }

    public static void Apagar(Funcionario objeto) {
        FuncionarioDAO.getInstance().Delete(objeto);
    }

}
