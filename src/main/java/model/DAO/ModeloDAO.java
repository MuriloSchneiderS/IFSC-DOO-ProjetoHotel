package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Modelo;

public class ModeloDAO implements InterfaceDAO<Modelo> {

    private static ModeloDAO instance;
    protected EntityManager entityManager;

    public ModeloDAO() {
        entityManager = getEntityManager();
    }

    public static ModeloDAO getInstance() {
        if (instance == null) {
            instance = new ModeloDAO();
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
    public void Create(Modelo objeto) {
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
    public Modelo Retrieve(int id) {

        Modelo modelo = entityManager.find(Modelo.class, id);
        return modelo;
    }

    @Override
    public List<Modelo> Retrieve(String atributo, String valor) {

        List<Modelo> listaModelo = new ArrayList<>();
        listaModelo = entityManager.createQuery(" Select x From Modelo x "
                + " where x." + atributo
                + " LIKE '%" + valor + "%'", Modelo.class).getResultList();
        return listaModelo;
    }

    @Override
    public void Update(Modelo objeto) {
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
    public void Delete(Modelo objeto) {

        try{
            entityManager.getTransaction().begin();
            Modelo modelo = new Modelo();
            modelo = entityManager.find(Modelo.class, objeto.getId());
            if(modelo != null){
                entityManager.remove(modelo);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}