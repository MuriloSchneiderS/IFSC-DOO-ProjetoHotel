package service;

import java.util.List;
import model.bo.Modelo;
import model.DAO.ModeloDAO;

public class ModeloService{

    public static void Criar(Modelo objeto) {
        ModeloDAO.getInstance().Create(objeto);
    }

    public static Modelo Carregar(int id) {
        return ModeloDAO.getInstance().Retrieve(id);
    }

    public static List<Modelo> Carregar(String atributo, String valor) {
        return ModeloDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(Modelo objeto) {
        ModeloDAO.getInstance().Update(objeto);
    }

    public static void Apagar(Modelo objeto) {
        ModeloDAO.getInstance().Delete(objeto);
    }

}
