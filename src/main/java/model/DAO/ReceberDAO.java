package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Receber;

public class ReceberDAO implements InterfaceDAO<Receber> {

    private static ReceberDAO instance;
    protected EntityManager entityManager;

    public ReceberDAO() {
        entityManager = getEntityManager();
    }

    public static ReceberDAO getInstance() {
        if (instance == null) {
            instance = new ReceberDAO();
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
    public void Create(Receber objeto) {
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
    public Receber Retrieve(int id) {

        Receber receber = entityManager.find(Receber.class, id);
        return receber;
    }

    @Override
    public List<Receber> Retrieve(String atributo, String valor) {

        List<Receber> listaReceber = new ArrayList<>();
        listaReceber = entityManager.createQuery(" Select hosp From receber hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", Receber.class).getResultList();
        return listaReceber;
    }

    @Override
    public void Update(Receber objeto) {
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
    public void Delete(Receber objeto) {

        try{
            entityManager.getTransaction().begin();
            Receber receber = new Receber();
            receber = entityManager.find(Receber.class, objeto.getId());
            if(receber != null){
                entityManager.remove(receber);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}