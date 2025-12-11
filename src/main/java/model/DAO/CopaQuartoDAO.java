package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DAO.InterfaceDAO;
import model.bo.CopaQuarto;

public class CopaQuartoDAO implements InterfaceDAO<CopaQuarto> {

    private static CopaQuartoDAO instance;
    protected EntityManager entityManager;

    public CopaQuartoDAO() {
        entityManager = getEntityManager();
    }

    public static CopaQuartoDAO getInstance() {
        if (instance == null) {
            instance = new CopaQuartoDAO();
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
    public void Create(CopaQuarto objeto) {
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
    public CopaQuarto Retrieve(int id) {

        CopaQuarto copaQuarto = entityManager.find(CopaQuarto.class, id);
        return copaQuarto;
    }

    @Override
    public List<CopaQuarto> Retrieve(String atributo, String valor) {

        List<CopaQuarto> listaCopaQuarto = new ArrayList<>();
        listaCopaQuarto = entityManager.createQuery(" Select hosp From copaQuarto hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", CopaQuarto.class).getResultList();
        return listaCopaQuarto;
    }

    @Override
    public void Update(CopaQuarto objeto) {
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
    public void Delete(CopaQuarto objeto) {

        try{
            entityManager.getTransaction().begin();
            CopaQuarto copaQuarto = new CopaQuarto();
            copaQuarto = entityManager.find(CopaQuarto.class, objeto.getId());
            if(copaQuarto != null){
                entityManager.remove(copaQuarto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}