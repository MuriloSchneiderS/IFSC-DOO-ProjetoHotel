package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Quarto;

public class QuartoDAO implements InterfaceDAO<Quarto> {

    private static QuartoDAO instance;
    protected EntityManager entityManager;

    public QuartoDAO() {
        entityManager = getEntityManager();
    }

    public static QuartoDAO getInstance() {
        if (instance == null) {
            instance = new QuartoDAO();
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
    public void Create(Quarto objeto) {
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
    public Quarto Retrieve(int id) {

        Quarto quarto = entityManager.find(Quarto.class, id);
        return quarto;
    }

    @Override
    public List<Quarto> Retrieve(String atributo, String valor) {

        List<Quarto> listaQuarto = new ArrayList<>();
        listaQuarto = entityManager.createQuery(" Select hosp From quarto hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", Quarto.class).getResultList();
        return listaQuarto;
    }

    @Override
    public void Update(Quarto objeto) {
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
    public void Delete(Quarto objeto) {

        try{
            entityManager.getTransaction().begin();
            Quarto quarto = new Quarto();
            quarto = entityManager.find(Quarto.class, objeto.getId());
            if(quarto != null){
                entityManager.remove(quarto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}