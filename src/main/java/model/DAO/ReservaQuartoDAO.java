package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.ReservaQuarto;

public class ReservaQuartoDAO implements InterfaceDAO<ReservaQuarto> {

    private static ReservaQuartoDAO instance;
    protected EntityManager entityManager;

    public ReservaQuartoDAO() {
        entityManager = getEntityManager();
    }

    public static ReservaQuartoDAO getInstance() {
        if (instance == null) {
            instance = new ReservaQuartoDAO();
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
    public void Create(ReservaQuarto objeto) {
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
    public ReservaQuarto Retrieve(int id) {

        ReservaQuarto reservaQuarto = entityManager.find(ReservaQuarto.class, id);
        return reservaQuarto;
    }

    @Override
    public List<ReservaQuarto> Retrieve(String atributo, String valor) {

        List<ReservaQuarto> listaReservaQuarto = new ArrayList<>();
        listaReservaQuarto = entityManager.createQuery(" Select hosp From reservaQuarto hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", ReservaQuarto.class).getResultList();
        return listaReservaQuarto;
    }

    @Override
    public void Update(ReservaQuarto objeto) {
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
    public void Delete(ReservaQuarto objeto) {

        try{
            entityManager.getTransaction().begin();
            ReservaQuarto reservaQuarto = new ReservaQuarto();
            reservaQuarto = entityManager.find(ReservaQuarto.class, objeto.getId());
            if(reservaQuarto != null){
                entityManager.remove(reservaQuarto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}