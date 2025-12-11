package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Check;

public class CheckDAO implements InterfaceDAO<Check> {

    private static CheckDAO instance;
    protected EntityManager entityManager;

    public CheckDAO() {
        entityManager = getEntityManager();
    }

    public static CheckDAO getInstance() {
        if (instance == null) {
            instance = new CheckDAO();
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
    public void Create(Check objeto) {
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
    public Check Retrieve(int id) {

        Check check = entityManager.find(Check.class, id);
        return check;
    }

    @Override
    public List<Check> Retrieve(String atributo, String valor) {

        List<Check> listaCheck = new ArrayList<>();
        listaCheck = entityManager.createQuery(" Select hosp From check hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", Check.class).getResultList();
        return listaCheck;
    }

    @Override
    public void Update(Check objeto) {
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
    public void Delete(Check objeto) {

        try{
            entityManager.getTransaction().begin();
            Check check = new Check();
            check = entityManager.find(Check.class, objeto.getId());
            if(check != null){
                entityManager.remove(check);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}