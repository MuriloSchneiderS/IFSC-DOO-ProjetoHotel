package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DAO.InterfaceDAO;
import model.bo.CheckQuarto;

public class CheckQuartoDAO implements InterfaceDAO<CheckQuarto> {

    private static CheckQuartoDAO instance;
    protected EntityManager entityManager;

    public CheckQuartoDAO() {
        entityManager = getEntityManager();
    }

    public static CheckQuartoDAO getInstance() {
        if (instance == null) {
            instance = new CheckQuartoDAO();
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
    public void Create(CheckQuarto objeto) {
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
    public CheckQuarto Retrieve(int id) {

        CheckQuarto checkQuarto = entityManager.find(CheckQuarto.class, id);
        return checkQuarto;
    }

    @Override
    public List<CheckQuarto> Retrieve(String atributo, String valor) {

        List<CheckQuarto> listaCheckQuarto = new ArrayList<>();
        listaCheckQuarto = entityManager.createQuery(" Select hosp From checkQuarto hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", CheckQuarto.class).getResultList();
        return listaCheckQuarto;
    }

    @Override
    public void Update(CheckQuarto objeto) {
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
    public void Delete(CheckQuarto objeto) {

        try{
            entityManager.getTransaction().begin();
            CheckQuarto checkQuarto = new CheckQuarto();
            checkQuarto = entityManager.find(CheckQuarto.class, objeto.getId());
            if(checkQuarto != null){
                entityManager.remove(checkQuarto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}