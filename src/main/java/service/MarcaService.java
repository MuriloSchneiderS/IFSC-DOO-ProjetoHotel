package service;

import java.util.List;
import model.bo.Marca;
import model.DAO.MarcaDAO;

public class MarcaService{

    public static void Criar(Marca objeto) {
        MarcaDAO.getInstance().Create(objeto);
    }

    public static Marca Carregar(int id) {
        return MarcaDAO.getInstance().Retrieve(id);
    }

    public static List<Marca> Carregar(String atributo, String valor) {
        return MarcaDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(Marca objeto) {
        MarcaDAO.getInstance().Update(objeto);
    }

    public static void Apagar(Marca objeto) {
        MarcaDAO.getInstance().Delete(objeto);
    }

}
