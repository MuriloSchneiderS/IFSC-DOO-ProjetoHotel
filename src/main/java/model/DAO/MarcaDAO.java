package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Marca;

public class MarcaDAO implements InterfaceDAO<Marca> {

    private static MarcaDAO instance;
    protected EntityManager entityManager;

    public MarcaDAO() {
        entityManager = getEntityManager();
    }

    public static MarcaDAO getInstance() {
        if (instance == null) {
            instance = new MarcaDAO();
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
    public void Create(Marca objeto) {
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
    public Marca Retrieve(int id) {

        Marca marca = entityManager.find(Marca.class, id);
        return marca;
    }

    @Override
    public List<Marca> Retrieve(String atributo, String valor) {

        List<Marca> listaMarca = new ArrayList<>();
        listaMarca = entityManager.createQuery(" Select x From Marca x "
                + " where x." + atributo
                + " LIKE '%" + valor + "%'", Marca.class).getResultList();
        return listaMarca;
    }

    @Override
    public void Update(Marca objeto) {
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
    public void Delete(Marca objeto) {

        try{
            entityManager.getTransaction().begin();
            Marca marca = new Marca();
            marca = entityManager.find(Marca.class, objeto.getId());
            if(marca != null){
                entityManager.remove(marca);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}