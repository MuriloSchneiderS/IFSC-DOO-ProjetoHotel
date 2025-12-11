package service;

import java.util.List;
import model.bo.Veiculo;
import model.DAO.VeiculoDAO;

public class VeiculoService{

    public static void Criar(Veiculo objeto) {
        VeiculoDAO.getInstance().Create(objeto);
    }

    public static Veiculo Carregar(int id) {
        return VeiculoDAO.getInstance().Retrieve(id);
    }

    public static List<Veiculo> Carregar(String atributo, String valor) {
        return VeiculoDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(Veiculo objeto) {
        VeiculoDAO.getInstance().Update(objeto);
    }

    public static void Apagar(Veiculo objeto) {
        VeiculoDAO.getInstance().Delete(objeto);
    }

}
