package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.VagaEstacionamento;

public class VagaEstacionamentoDAO implements InterfaceDAO<VagaEstacionamento> {

    private static VagaEstacionamentoDAO instance;
    protected EntityManager entityManager;

    public VagaEstacionamentoDAO() {
        entityManager = getEntityManager();
    }

    public static VagaEstacionamentoDAO getInstance() {
        if (instance == null) {
            instance = new VagaEstacionamentoDAO();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void Create(VagaEstacionamento objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public VagaEstacionamento Retrieve(int id) {

        VagaEstacionamento vagaEstacionamento = entityManager.find(VagaEstacionamento.class, id);
        return vagaEstacionamento;
    }

    @Override
    public List<VagaEstacionamento> Retrieve(String atributo, String valor) {

        List<VagaEstacionamento> listaVagaEstacionamento = new ArrayList<>();
        listaVagaEstacionamento = entityManager.createQuery(" Select hosp From vagaEstacionamento hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", VagaEstacionamento.class).getResultList();
        return listaVagaEstacionamento;
    }

    @Override
    public void Update(VagaEstacionamento objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void Delete(VagaEstacionamento objeto) {

        try{
            entityManager.getTransaction().begin();
            VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
            vagaEstacionamento = entityManager.find(VagaEstacionamento.class, objeto.getId());
            if(vagaEstacionamento != null){
                entityManager.remove(vagaEstacionamento);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}