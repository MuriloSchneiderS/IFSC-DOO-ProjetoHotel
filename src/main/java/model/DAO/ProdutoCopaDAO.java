package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.ProdutoCopa;

public class ProdutoCopaDAO implements InterfaceDAO<ProdutoCopa> {

    private static ProdutoCopaDAO instance;
    protected EntityManager entityManager;

    public ProdutoCopaDAO() {
        entityManager = getEntityManager();
    }

    public static ProdutoCopaDAO getInstance() {
        if (instance == null) {
            instance = new ProdutoCopaDAO();
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
    public void Create(ProdutoCopa objeto) {
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
    public ProdutoCopa Retrieve(int id) {

        ProdutoCopa produtoCopa = entityManager.find(ProdutoCopa.class, id);
        return produtoCopa;
    }

    @Override
    public List<ProdutoCopa> Retrieve(String atributo, String valor) {

        List<ProdutoCopa> listaProdutoCopa = new ArrayList<>();
        listaProdutoCopa = entityManager.createQuery(" Select x From produto_copa x "
                + " where x." + atributo
                + " LIKE '%" + valor + "%'", ProdutoCopa.class).getResultList();
        return listaProdutoCopa;
    }

    @Override
    public void Update(ProdutoCopa objeto) {
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
    public void Delete(ProdutoCopa objeto) {

        try{
            entityManager.getTransaction().begin();
            ProdutoCopa produtoCopa = new ProdutoCopa();
            produtoCopa = entityManager.find(ProdutoCopa.class, objeto.getId());
            if(produtoCopa != null){
                entityManager.remove(produtoCopa);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}