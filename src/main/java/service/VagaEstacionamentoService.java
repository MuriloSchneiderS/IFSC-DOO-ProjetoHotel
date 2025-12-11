package service;

import java.util.List;
import model.bo.VagaEstacionamento;
import model.DAO.VagaEstacionamentoDAO;

public class VagaEstacionamentoService{

    public static void Criar(VagaEstacionamento objeto) {
        VagaEstacionamentoDAO.getInstance().Create(objeto);
    }

    public static VagaEstacionamento Carregar(int id) {
        return VagaEstacionamentoDAO.getInstance().Retrieve(id);
    }

    public static List<VagaEstacionamento> Carregar(String atributo, String valor) {
        return VagaEstacionamentoDAO.getInstance().Retrieve(atributo, valor);
    }

    public static void Atualizar(VagaEstacionamento objeto) {
        VagaEstacionamentoDAO.getInstance().Update(objeto);
    }

    public static void Apagar(VagaEstacionamento objeto) {
        VagaEstacionamentoDAO.getInstance().Delete(objeto);
    }

}
